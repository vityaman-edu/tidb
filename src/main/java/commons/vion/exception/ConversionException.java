//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.exception;

public class ConversionException extends Exception {
    public ConversionException(Throwable cause) {
        super("Can't convert to vion: " + cause.getMessage(), cause);
    }
}
