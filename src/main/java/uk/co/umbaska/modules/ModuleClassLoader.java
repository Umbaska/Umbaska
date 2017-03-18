package uk.co.umbaska.modules;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Class that represents the classloader to load classes from a Module
 * @author Nicofisi
 */
public class ModuleClassLoader extends URLClassLoader {
    /**
     * Constructs a ModuleClassLoader for the specified {@code urls} and {@code parent} {@link ClassLoader ClassLoaders}.
     * @param urls the URLs for the classes in an {@link UmbaskaModule}
     * @param parent the parent ClassLoader for an {@link UmbaskaModule}
     */
    ModuleClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

    /**
     * Adds a {@link URL} to the {@link URL URLs} in this ClassLoader
     * @param url the url to be added
     */
	@Override
	public void addURL(URL url) {
		super.addURL(url);
	}
}
