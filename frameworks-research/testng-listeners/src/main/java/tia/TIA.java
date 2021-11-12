package tia;

import java.util.ArrayList;
import java.util.List;

public class TIA {
    private static List<String> excludedMethods = new ArrayList<>();

    public static boolean saysSkip(String methodName) {
        return excludedMethods.contains(methodName);
    }

    public static List<String> addExcludedMethod(String excludedMethod) {
        TIA.excludedMethods.add(excludedMethod);
        return excludedMethods;
    }
}
