package uk.co.umbaska.modules;

import uk.co.umbaska.Umbaska;

import java.io.File;
import java.util.ArrayList;

public class ModuleInfo {
	public static ArrayList<ModuleInfo> moduleInfos = new ArrayList<>(5);

	private String name, mainClassPath, basePackage, version;
	private int minMajorJavaVersion, minUmbaskaBuildNumber;
	private File moduleFile;
	private ClassLoader classLoader;
	private UmbaskaModule umbaskaModule;

	public static ModuleInfo getInfoByName(String name) {
		for (ModuleInfo info : moduleInfos) {
			if (info.getName().equals(name)) {
				return info;
			}
		}
		return null;
	}

	public static ModuleInfo getModuleInfoFor(
			File moduleFile, String basePackage, String name, String version, String mainClassPath,
			int minUmbaskaBuildNumber, int minMajorJavaVersion, ClassLoader classLoader) {

		for (ModuleInfo info : moduleInfos) {
			if (info.getModuleFile().getAbsolutePath().equals(moduleFile.getAbsolutePath())) {
				return info;
			} else if (info.getName().equals(name)) {
				Umbaska.getInstance().getLogger().severe("Two different modules tried to load with the same name - '" + name + "'");
                Umbaska.getInstance().getLogger().severe("Location of the one which tried to load: " + moduleFile.getAbsolutePath());
                Umbaska.getInstance().getLogger().severe("Location of the already loaded one: " + info.getModuleFile().getAbsolutePath());
				return null;
			}
		}
		ModuleInfo outInfo = new ModuleInfo(
				moduleFile, basePackage, name, version, mainClassPath,
				minUmbaskaBuildNumber, minMajorJavaVersion, classLoader);
		moduleInfos.add(outInfo);
		return outInfo;
	}

	private ModuleInfo(File moduleFile, String basePackage, String name, String version,
                       String mainClassPath, int minUmbaskaBuildNumber, int minMajorJavaVersion, ClassLoader classLoader) {
		this.moduleFile = moduleFile;
		this.basePackage = basePackage;
		this.name = name;
		this.version = version;
		this.minUmbaskaBuildNumber = minUmbaskaBuildNumber;
		this.mainClassPath = mainClassPath;
		this.minMajorJavaVersion = minMajorJavaVersion;
		this.classLoader = classLoader;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public UmbaskaModule getUmbaskaModule() {
		return umbaskaModule;
	}

	public int getMinUmbaskaBuildNumber() {
		return minUmbaskaBuildNumber;
	}

	public String getMainClassPath() {
		return mainClassPath;
	}

	public File getModuleFile() {
		return moduleFile;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public int getMinMajorJavaVersion() {
		return minMajorJavaVersion;
	}

}
