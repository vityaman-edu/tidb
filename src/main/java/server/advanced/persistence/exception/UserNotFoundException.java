//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.persistence.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UserNotFoundException(Throwable throwable) {
        super("User not found: " + throwable.getMessage(), throwable);
    }

    public UserNotFoundException(String message) {
        super("User not found: " + message);
    }
}
