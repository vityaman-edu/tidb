//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter.exception;

public class InterpreterException extends Exception {
    public InterpreterException(String message) {
        super(message);
    }

    public InterpreterException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public InterpreterException(String message, Throwable cause) {
        super(message, cause);
    }
}
