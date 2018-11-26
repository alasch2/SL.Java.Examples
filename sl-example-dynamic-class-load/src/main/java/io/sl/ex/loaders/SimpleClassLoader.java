package io.sl.ex.loaders;

public class SimpleClassLoader extends ClassLoader {
	
	public SimpleClassLoader() {
		super();
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		
		System.out.println("Loading class " + name);
		return super.loadClass(name);
//		name.replace("/",  ".");
//		if (!name.startsWith("io.sealights")) {
//			return super.loadClass(name);
//		}
//		
//		return null;
	}

}
