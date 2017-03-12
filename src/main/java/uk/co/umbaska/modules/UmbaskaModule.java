package uk.co.umbaska.modules;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import uk.co.umbaska.Umbaska;
import uk.co.umbaska.registrations.AutoRegisteringSkriptElement;
import uk.co.umbaska.registrations.SyntaxLoader;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

@SuppressWarnings({"WeakerAccess", "unused"})
public class UmbaskaModule {
	private ModuleInfo moduleInfo;
	private boolean enabled = false;
	private Logger logger;


	public final void setModuleInfo(ModuleInfo moduleInfo) {
		this.moduleInfo = moduleInfo;
	}

	public final ModuleInfo getModuleInfo() {
		return moduleInfo;
	}

	protected void setEnabled(boolean b) {
		enabled = b;
	}

	public final boolean isEnabled() {
		return enabled;
	}

	/**
	 * Called when a module is being enabled. You can safely override it.
	 * If you want to enable a module, use {@link #enable()} instead
	 */
	protected void onEnable() {

	}

	public final void enable() {

		logger = Logger.getLogger(moduleInfo.getName());
		onEnable();
		enabled = true;
	}

	public Logger getLogger(){
		return logger;
	}

    /**
     * Register the syntaxes
     */
	public void registerSyntaxes(){
	    try {
            SyntaxLoader syntaxLoader = new SyntaxLoader();
            ClassLoader clazzLoader = moduleInfo.getClassLoader();
            Class<?> clazz = clazzLoader.getClass().getSuperclass().getSuperclass().getSuperclass();
            Field f = clazz.getDeclaredField("classes");
            f.setAccessible(true);

            Vector<Class> classes = (Vector<Class>) ((Vector<Class>) f.get(clazzLoader)).clone();
            List<Class<? extends AutoRegisteringSkriptElement>> elements = new ArrayList<>();
            for (Class cla : classes) {
                if (AutoRegisteringSkriptElement.class.isAssignableFrom(cla)) {
                    elements.add(cla);
                }
            }
            for (Class<? extends AutoRegisteringSkriptElement> cla : elements) {
                syntaxLoader.load(cla);
            }
            logger.info("Loaded: ");
            logger.info(String.format("%d Expressions", syntaxLoader.getLoadedExpressions()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        // TODO : Print Totals
    }

	/**
	 * Called when a module is being disabled. You can safely override it.
	 * If you want to disable a module, use {@link #disable()} instead
	 */
	protected void onDisable() {

	}

	/**
	 * Disables a module. Notice that all it does is calls the {@link #onDisable()} method
	 * and marks a module as disabled, it doesn't unregister the effects, expressions etc from Skript
	 */
	public final void disable() {
		enabled = false;
		onDisable();
	}
	
	public final Umbaska getUmbaska() {
		return Umbaska.getInstance();
	}

	/**
	 * @return Same as JavaPlugin#getDataFolder(). If it's not null, it exists and is a directory
	 */
	public final File getDataFolder() {
		ModuleInfo info = getModuleInfo();
		File file;
		try {
			file = new File(ModuleManager.modulesDir, URLEncoder.encode(info.getName(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		if (file.exists()) {
			if (!file.mkdir()) {
				return null;
			}
		}
		return file;
	}
}
