package restservice.helpers;

public class BooleanHelper {
    public static boolean isNotNullAndNotBlank(Object o) {
        return o != null && !o.toString().isBlank();
    }
}

