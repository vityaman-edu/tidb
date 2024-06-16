//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.exception;

import java.text.ParseException;

public class UncheckedParseException extends RuntimeException {
    public UncheckedParseException(ParseException e) {
        super(e.getMessage(), e);
    }
}
