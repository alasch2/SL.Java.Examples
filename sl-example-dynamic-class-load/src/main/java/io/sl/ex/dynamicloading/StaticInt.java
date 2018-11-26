package io.sl.ex.dynamicloading;

import io.sl.ex.loaders.DynamicClassLoader;
import io.sl.ex.utils.ReflectUtil;

import static java.lang.System.*;

public class StaticInt {
	private static final String CLASS_NAME = "io.sl.ex.example1.StaticInt$User";
	private static final String CLASS_PATH = "target/classes";
	public static void main(String[] args) {
		Class<?> userClass1 = User.class;
		Class<?> userClass2 = new DynamicClassLoader(CLASS_PATH).load(CLASS_NAME);
		if (userClass2 == null) {
			out.println(String.format("DynamicClassLoader failed to load class '%s' from path '%s'", 
					CLASS_NAME, CLASS_PATH));
		}
	}

	private static void exmapleCodeNotFitTheFlow() {
		Class<?> userClass1 = User.class;
		Class<?> userClass2 = new DynamicClassLoader("target/classes")
				.load("io.sl.ex.example1.StaticInt$User");
		
		out.println("Seems to be the same class:");
		out.println(userClass1.getName());
		out.println(userClass2.getName());
		out.println();

		out.println("But why there are 2 different class loaders:");
		out.println(userClass1.getClassLoader());
		out.println(userClass2.getClassLoader());
		out.println();

		User.age = 11;
		out.println("And different age values:");
		out.println((int) ReflectUtil.getStaticFieldValue("age", userClass1));
		out.println((int) ReflectUtil.getStaticFieldValue("age", userClass2));
	}
	
	public static class User {
		public static int age = 10;
	}
}