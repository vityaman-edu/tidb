//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter.exception;

public class ExecutionException extends InterpreterException {
    public ExecutionException(String message) {
        super(message);
    }

    public ExecutionException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

    public ExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
