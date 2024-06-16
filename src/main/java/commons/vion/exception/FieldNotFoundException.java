//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.exception;

import commons.vion.VionObject;

public class FieldNotFoundException extends FieldExtractionException {
    public FieldNotFoundException(VionObject json, String key) {
        super(json, String.format("Filed with key '%s' not found", key));
    }
}
