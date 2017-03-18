package uk.co.umbaska.modules;

import uk.co.umbaska.Umbaska;

import java.io.File;
import java.util.ArrayList;

/**
 * Class that represents all the info for a {@link uk.co.umbaska.modules.UmbaskaModule}
 * @author Nicofisi
 */
public class ModuleInfo {
	public static ArrayList<ModuleInfo> moduleInfos = new ArrayList<>(5);

	private String name, mainClassPath, basePackage, version, minUmbaskaBuild;
	private int minMajorJavaVersion;
	private File moduleFile;
	private ClassLoader classLoader;
	private UmbaskaModule umbaskaModule;

    /**
     * Gets a {@link ModuleInfo} by a {@code name}
     * @param name the name of the {@link UmbaskaModule} to retrieve the {@link ModuleInfo} for
     * @return the ModuleInfo for a specified {@code name}
     */
	public static ModuleInfo getInfoByName(String name) {
		for (ModuleInfo info : moduleInfos) {
			if (info.getName().equals(name)) {
				return info;
			}
		}
		return null;
	}

    /**
     * Creates a ModuleInfo for the specified parameters
     * @param moduleFile the File for the JAR File of the {@link UmbaskaModule}
     * @param basePackage the base package of the {@link UmbaskaModule}
     * @param name the name of the {@link UmbaskaModule}
     * @param version the version of the {@link UmbaskaModule}
     * @param mainClassPath the path to the main class ({@link Class#getCanonicalName()} for the {@link UmbaskaModule}
     * @param minUmbaskaBuild the minimum Umbaska build needed for the {@link UmbaskaModule}
     * @param minMajorJavaVersion the minimum major java version for the {@link UmbaskaModule}
     * @param classLoader the {@link ClassLoader} for the {@link UmbaskaModule}
     * @return the constructed ModuleInfo
     */
	public static ModuleInfo getModuleInfoFor(
			File moduleFile, String basePackage, String name, String version, String mainClassPath,
			String minUmbaskaBuild, int minMajorJavaVersion, ClassLoader classLoader) {

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
				minUmbaskaBuild, minMajorJavaVersion, classLoader);
		moduleInfos.add(outInfo);
		return outInfo;
	}

	private ModuleInfo(File moduleFile, String basePackage, String name, String version,
                       String mainClassPath, String minUmbaskaBuild, int minMajorJavaVersion, ClassLoader classLoader) {
		this.moduleFile = moduleFile;
		this.basePackage = basePackage;
		this.name = name;
		this.version = version;
		this.minUmbaskaBuild = minUmbaskaBuild;
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

	public String getMinUmbaskaBuild() {
		return minUmbaskaBuild;
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
