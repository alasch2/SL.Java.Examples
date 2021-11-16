package impl;

import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class JavaOverrideMethodTest {
    @Test
    public void test() throws NoSuchMethodException {
        printMethodOverrideInfo(ClassWithOverrideToString.class);
        printMethodOverrideInfo(ClassWithOverrideToStringInParent.class);
        printMethodOverrideInfo(ClassWithoutOverrideToString.class);
    }

    private static void printMethodOverrideInfo(Class clazz) throws NoSuchMethodException {
        Method method = clazz.getMethod("toString");
        System.out.println("Class: " + clazz + ", declaringClass: " + method.getDeclaringClass());
    }

    public static abstract class BaseClass {
        protected String field1;
        protected String field2;

        public BaseClass(String field1, String field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }

    public static class ClassWithOverrideToString extends BaseClass {
        public ClassWithOverrideToString(String field1, String field2) {
            super(field1, field2);
        }

        @Override
        public String toString() {
            return "ClassWithOverrideToString{" +
                    "field1='" + field1 + '\'' +
                    ", field2='" + field2 + '\'' +
                    '}';
        }
    }

    public static class ClassWithOverrideToStringInParent extends ClassWithOverrideToString {
        ClassWithOverrideToStringInParent(String field1, String field2) {
            super(field1, field2);
        }
    }

    public static class ClassWithoutOverrideToString extends BaseClass {
        public ClassWithoutOverrideToString(String field1, String field2) {
            super(field1, field2);
        }
    }

}
