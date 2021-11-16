package utils;

import java.lang.reflect.Field;

public class ReflectionUtils {
    public static <T> T getFieldValue(Class objectClass, Object o, String fieldName) {
        Field field;
        try {
            field = objectClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(o);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
