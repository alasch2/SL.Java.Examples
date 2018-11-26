package io.sl.ex.loaders;

public class InnerClassLoader {
	
	public static class InnerSimpleClassLoader extends ClassLoader {
		
	public InnerSimpleClassLoader() {
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

}
