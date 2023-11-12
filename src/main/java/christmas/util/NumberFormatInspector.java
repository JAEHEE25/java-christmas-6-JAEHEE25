package christmas.util;

public class NumberFormatInspector {
    public static boolean isNumeric(String value) {
        try {
            Parser.toInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
