package io.sl.ex.loaders;

import java.util.HashSet;
import java.util.Set;

/**
 * Load all classes it can, leave the rest to the Parent ClassLoader
 */
public abstract class AggressiveClassLoader extends ClassLoader {

	Set<String> loadedClasses = new HashSet<>();
	Set<String> unavailableClasses = new HashSet<>();
	private ClassLoader parent = String.class.getClassLoader();

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (loadedClasses.contains(name) || unavailableClasses.contains(name)) {
			return super.loadClass(name); // Use default CL cache
		}
		
		byte[] newClassData = loadNewClass(name);
		if (newClassData != null) {
			loadedClasses.add(name);
			return loadClass(newClassData, name);
		} 
		else {
			unavailableClasses.add(name);
			if (parent != null) {
				return parent.loadClass(name);
			}
		}
		return null;
	}

	    public AggressiveClassLoader setParent(ClassLoader parent) {
	        this.parent = parent;
	        return this;
	    }

	/**
	 * Handle exception
	 * @param name
	 * @return
	 */
	public Class<?> load(String name) {
		try {
			return loadClass(name);
		} 
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract byte[] loadNewClass(String name);

	public Class<?> loadClass(byte[] classData, String name) {
		Class<?> clazz = defineClass(name, classData, 0, classData.length);
		if (clazz != null) {
			if (clazz.getPackage() == null) {
				definePackage(name.replaceAll("\\.\\w+$", ""), null, null, null, null, null, null, null);
			}
			resolveClass(clazz);
		}
		return clazz;
	}

	public static String toFilePath(String name) {
		return name.replaceAll("\\.", "/") + ".class";
	}
}