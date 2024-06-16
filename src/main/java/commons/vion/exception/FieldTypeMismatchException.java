//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.exception;

import commons.vion.VionObject;

public class FieldTypeMismatchException extends FieldExtractionException {
    public FieldTypeMismatchException(VionObject json, String key, Class<?> expected, Class<?> found) {
        super(json, String.format("Type mismatch for field '%s': %s expected, found: %s", key, expected, found));
    }
}
