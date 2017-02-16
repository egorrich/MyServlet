package utils;

/**
 * Created by egor on 15.2.17.
 */
public class SessionValidator {

    public static Boolean validate(String name, String password) {
        Boolean result = false;
        if ((name != null) && (password != null)) {
            result = true;
        }
        return result;
    }

}
