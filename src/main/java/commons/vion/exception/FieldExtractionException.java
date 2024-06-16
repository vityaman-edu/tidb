//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.exception;

import commons.vion.VionObject;

public class FieldExtractionException extends Exception {
    public FieldExtractionException(VionObject json, Throwable cause) {
        super(String.format("Error in %s: %s", json, cause.getMessage()), cause);
    }

    public FieldExtractionException(VionObject json, String explanation) {
        super(String.format("Error in %s: %s", json, explanation));
    }
}
