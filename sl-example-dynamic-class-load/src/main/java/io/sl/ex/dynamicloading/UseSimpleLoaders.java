package io.sl.ex.dynamicloading;

import io.sl.ex.loaders.InnerClassLoader;
import io.sl.ex.loaders.SimpleClassLoader;

public class UseSimpleLoaders {

	private static final String USER_CLASS_NAME = "io.sl.ex.dynamicloading.UseSimpleLoaders$User";
	private static final String CLASS_PATH = "target/classes";
	
	public static void main(String[] args) throws ClassNotFoundException {
//		Class<?> userClass1 = User.class;
//		Class<?> userClass2 = new InnerClassLoader.InnerSimpleClassLoader().loadClass(USER_CLASS_NAME);
//		if (userClass2 == null) {
//			out.println(String.format("InnerSimpleClassLoader failed to load class '%s' from path '%s'", 
//					USER_CLASS_NAME, CLASS_PATH));
//		}
	}
	
	public void executeWithSimpleLoader() throws Exception {
//		User user = createUser(new SimpleClassLoader());
		User  user = createUser("io.sl.ex.loaders.SimpleClassLoader");
		System.out.println("Created " + user + " with SimpleClassLoader");
	}
	
	public void executeWithInnerLoader() throws Exception {
//		User user = createUser(new InnerClassLoader.InnerSimpleClassLoader());
		User  user = createUser("io.sl.ex.loaders.InnerClassLoader$InnerSimpleClassLoader");
		System.out.println("Created " + user + " with InnerSimpleClassLoader");
	}
	
	public User createUser(String loaderName) throws Exception {
		Class<?> loaderClass = ClassLoader.class.forName(loaderName);
		ClassLoader loader = (ClassLoader)loaderClass.newInstance();
		Class<?> userClass = loader.loadClass(USER_CLASS_NAME);
		return (User) userClass.newInstance();
	}
	
	public User createUser(ClassLoader loader) throws Exception {
		Class<?> userClass = loader.loadClass(USER_CLASS_NAME);
		return (User) userClass.newInstance();
	}

	public static class User {
		public static int age = 10;
	}
}
