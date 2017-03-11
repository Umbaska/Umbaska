package uk.co.umbaska.modules;

import java.net.URL;
import java.net.URLClassLoader;

public class ModuleClassLoader extends URLClassLoader {
	ModuleClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	@Override
	public void addURL(URL url) {
		super.addURL(url);
	}
}
