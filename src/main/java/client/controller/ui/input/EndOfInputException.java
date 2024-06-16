//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui.input;

public class EndOfInputException extends RuntimeException {
    public EndOfInputException(String message) {
        super(message);
    }

    public EndOfInputException(EndOfInputException e) {
        super(e.getMessage(), e);
    }
}
