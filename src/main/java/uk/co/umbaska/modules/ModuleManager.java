package uk.co.umbaska.modules;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import uk.co.umbaska.Umbaska;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@SuppressWarnings("WeakerAccess") // Module developers may want to use some of the methods
public class ModuleManager {
	public static File modulesDir = null;
	private static ArrayList<ModuleInfo> modulesToEnable = new ArrayList<>(3);
	public static ArrayList<UmbaskaModule> enabledModules = new ArrayList<>();

	public static boolean prepare() {
		modulesDir = new File(Umbaska.getInstance().getDataFolder(), "modules");
		if (!modulesDir.exists()) {
			Umbaska.getInstance().getLogger().info("Creating directory " + modulesDir.getAbsolutePath());
			if (!modulesDir.mkdir()) {
				Umbaska.getInstance().getLogger().severe("Could not create the directory. Aborted loading modules");
				return false;
			}
		}
		return true;
	}

	/*
	 * name: Discord Module
	 * main: uk.co.umbaska.modules.discord.DiscordModule
	 * base-package: uk.co.umbaska.modules.discord
	 * version: 0.1.0
	 * version-integer: 1
	 * min-umbaska-build-number: 1
	 * min-major-java-version: 69
	 */

	public static void loadAndEnableModules() {
		loadModules();
		enableModules();
	}

	private static void loadModules() {
		File[] files = modulesDir.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if (file.isDirectory()) {
				continue;
			} else if (!file.getName().endsWith(".jar")) {
				continue;
			}
			ModuleInfo info = loadModule(file);
			if (info == null) {
				continue;
			}
			modulesToEnable.add(info);
		}
	}

	public static void enableModules() {
		for (ModuleInfo info : modulesToEnable) {
			UmbaskaModule module = initMainClass(info);
			if (module != null) {
				module.enable();
				enabledModules.add(module);
			}
		}
		if (modulesToEnable.size() > 0) {
			Umbaska.getInstance().getLogger().info("All modules have completed loading!");
			modulesToEnable.clear();
		}
	}

	public static void disableModules() {
		for (ModuleInfo info : ModuleInfo.moduleInfos) {
			UmbaskaModule module = info.getUmbaskaModule();
			if (module != null) {
				if (!module.isEnabled()) {
					module.disable();
				}
			}
		}
	}

	public static UmbaskaModule initMainClass(ModuleInfo info) {
		Umbaska.getInstance().getLogger().info(String.format("Enabling module %s v%s", info.getName(), info.getVersion()));
		Class<?> cla;
		try {
			cla = info.getClassLoader().loadClass(info.getMainClassPath());
		} catch (ClassNotFoundException ex) {
			Umbaska.getInstance().getLogger().severe("Could not load the main class of " + info.getName() + " - didn't find it");
			ex.printStackTrace();
			return null;
		}
		UmbaskaModule moduleMain;
		try {
			moduleMain = (UmbaskaModule) cla.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			ex.printStackTrace();
			return null;
		} catch (ClassCastException ex) {
			Umbaska.getInstance().getLogger().severe("The main class of " + info.getName() + " specified in its module.yml doesn't extend uk.co.umbaska.modules.UmbaskaModule");
			ex.printStackTrace();
			return null;
		}

		moduleMain.setModuleInfo(info);
		return moduleMain;
	}

	public static File downloadModuleFromUrl(String url) {
		try {
			File tempFile = File.createTempFile("umbaska-module", ".jar");
			FileUtils.copyURLToFile(new URL(url), tempFile);
			return tempFile;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static ModuleClassLoader getModuleClassLoader(File moduleFile) {
		URL url = null;
		String moduleFilePath = moduleFile.getAbsolutePath();
		moduleFilePath = moduleFilePath.replace(" ", "%20");
		try {
			url = new URL("jar:file:" + moduleFilePath + "!/");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			return null;
		}
		ModuleClassLoader cl = new ModuleClassLoader(
				((URLClassLoader) ModuleManager.class.getClassLoader()).getURLs(),
				ModuleManager.class.getClassLoader());
		cl.addURL(url);
		return cl;
	}

	public static ModuleInfo loadModule(File moduleFile) {
		YamlConfiguration moduleYml = null;
		try {
			ZipFile zipFile = new ZipFile(moduleFile);
			ZipEntry moduleZipEntry = zipFile.getEntry("module.yml");
			moduleYml = YamlConfiguration.loadConfiguration(new StringReader(IOUtils.toString(zipFile.getInputStream(moduleZipEntry), "UTF-8")));
		} catch (IOException e) {
			e.printStackTrace();
		}


		final String name = moduleYml.getString("name");
		final String mainClassPath = moduleYml.getString("main");
		String tbasePackage = moduleYml.getString("base-package");
		if(tbasePackage == null) {
			String[] splitMainClassPath = mainClassPath.split(".");
			splitMainClassPath[splitMainClassPath.length - 1] = null;
			for(String s : splitMainClassPath) {
				if(s == null) {
					continue;
				} else if(tbasePackage == null) {
					tbasePackage = s;
				} else {
					tbasePackage = tbasePackage + "." + s;
				}
			}
		}
		final String basePackage = tbasePackage;
		final String version = moduleYml.getString("version");
		final int minUmbaskaBuildNumber = moduleYml.getInt("min-umbaska-build-number");
		final int minMajorJavaVersion = moduleYml.getInt("min-major-java-version");
		/*if (minUmbaskaBuildNumber > Umbaska.VERSION_INTEGER) {
			Umbaska.moduleManagerWarning("The module ", name, " v", version, " (", versionInteger,
					") requires the latest version of Umbaska to work properly. (current Umbaska.VERSION_INTEGER: ",
					Umbaska.VERSION_INTEGER, "; required at least: ", minUmbaskaBuildNumber, ")");
			return null;
		}*/
		Umbaska.getInstance().getLogger().info(String.format("Loading module %s v%s", name, version));
		ModuleClassLoader loader = getModuleClassLoader(moduleFile);

		loadClasses(moduleFile, basePackage, loader);

		ModuleInfo info = ModuleInfo.getModuleInfoFor(moduleFile, basePackage, name, version,mainClassPath, minUmbaskaBuildNumber, minMajorJavaVersion, loader);
		if (info == null) {
			Umbaska.getInstance().getLogger().severe("Could not load the details of module at " + moduleFile.getAbsolutePath());
		}
		return info;
	}

	@SuppressWarnings("resource")
	public static boolean loadClasses(File moduleFile, String basePackage, ModuleClassLoader loader) {
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(moduleFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (jarFile == null) {
			return false;
		}
		Enumeration<JarEntry> e = jarFile.entries();

		while (e.hasMoreElements()) {
			JarEntry je = e.nextElement();
			if (je.isDirectory() || !je.getName().endsWith(".class")) {
				continue;
			}
			// -6 because of .class
			String className = je.getName().substring(0, je.getName().length() - 6);
			className = className.replace('/', '.');
			try {
				if (className.startsWith(basePackage)) {
					loader.loadClass(className);
				}
			} catch (ClassNotFoundException | NullPointerException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public static ArrayList<UmbaskaModule> getEnabledModules() {
		return enabledModules;
	}
}
