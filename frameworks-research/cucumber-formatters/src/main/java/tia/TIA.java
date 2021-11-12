package tia;

import java.util.ArrayList;
import java.util.List;

public class TIA {

    private static List<String> excludedMethods = new ArrayList<>();
    public static final String SKIPPED_BY_TIA = "@skipped_by_tia";
    
    public static boolean toSkip(String methodName) {
        return excludedMethods.contains(methodName);
    }

    public static List<String> addExcludedMethod(String excludedMethod) {
        TIA.excludedMethods.add(excludedMethod);
        return excludedMethods;
    }
}
