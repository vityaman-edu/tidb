//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter.exception;

public class NoSuchInstructionException extends InterpreterException {
    public NoSuchInstructionException(String keyword) {
        super("No such command: " + keyword);
    }
}
