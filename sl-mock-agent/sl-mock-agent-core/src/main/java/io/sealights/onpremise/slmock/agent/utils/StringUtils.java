package io.sealights.onpremise.slmock.agent.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ala schneider   Oct 18, 2017
 *
 */
public final class StringUtils {

    public static final String EMPTY_STRING = "";
    public static final String NEW_LINE = "\n";
    public static final String TAB = "\t";
    public static final String EOL = "\n";

    private static final String COMMA_SEPARATOR = ",";
    private static final String QUOTE = "\"";
    private static final String SINGLE_QUOTE = "'";

    public static boolean isNotEmpty(String str){
        return !isNullOrEmpty(str);
    }

    public static boolean isNullOrEmpty(String str){
        return (str == null || EMPTY_STRING.equals(str));
    }

    public static String join(List<String> strings, char delimiter) {

        if (strings == null){
            return null;
        }

        StringBuilder retString = new StringBuilder();

        if (!strings.isEmpty()){
            for(int i=0; i<strings.size()-1; i++){
                retString.append(strings.get(i));
                retString.append(delimiter);
            }
            retString.append(strings.get(strings.size() - 1));
        }

        return retString.toString();
    }

    public static String join(List<String> strings, String delimiter) {

        if (strings == null){
            return null;
        }

        StringBuilder retString = new StringBuilder();

        if (!strings.isEmpty()){
            for(int i=0; i<strings.size()-1; i++){
                retString.append(strings.get(i));
                retString.append(delimiter);
            }
            retString.append(strings.get(strings.size() - 1));
        }

        return retString.toString();
    }

    public static String capitalizeFirstCharacter(String str) {
        if (isNullOrEmpty(str))
            return str;

        char first = Character.toUpperCase(str.charAt(0));
        str = first + str.substring(1);
        return str;
    }

    public static String removeWrappingQuotes(String str) {
        if (isNullOrEmpty(str)){
            return str;
        }
        return str.replaceAll("^\"|\"$", "");
    }

    public static String inQuotes(String value) {
        if (value != null) {
            return QUOTE + value + QUOTE;
        }
        else {
            return value;
        }
    }

    public static String inSingleQuotes(String value) {
        if (value != null) {
            return SINGLE_QUOTE + value + SINGLE_QUOTE;
        }
        else {
            return value;
        }
    }

    public static List<String> splitByComma(String s) {
        return split(s, COMMA_SEPARATOR);
    }

    public static List<String> split(String s, String separator) {
        if (StringUtils.isNullOrEmpty(s)) {
            return Collections.emptyList();
        }

        return Arrays.asList(s.split(separator));
    }

    private StringUtils() {
    }
}
