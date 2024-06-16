//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.exception;

import commons.vion.VionObject;

public class DeserializationException extends Exception {
    public DeserializationException(VionObject json, Throwable cause) {
        super(String.format("Invalid vion %s: %s", json, cause.getMessage()), cause);
    }

    public DeserializationException(VionObject json, String explanation) {
        super(String.format("Invalid vion %s: %s", json, explanation));
    }
}
