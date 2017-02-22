package utils;

/**
 * Create on 15.2.17.

 * @author egor
 */
public class SessionValidator {

    public static Boolean validate(String name, String password) {
        return name != null && password != null;
    }
}
