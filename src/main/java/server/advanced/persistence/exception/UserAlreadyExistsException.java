//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.persistence.exception;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UserAlreadyExistsException(Throwable throwable) {
        super("User already exists: " + throwable.getMessage(), throwable);
    }
}
