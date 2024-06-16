//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion;

import commons.vion.exception.FieldExtractionException;
import java.util.Map;
import java.util.Set;

public interface VionObject {
    void remove(String var1);

    Set<String> keys();

    void put(String var1, VionObject var2);

    <T extends Number> void put(String var1, T var2);

    void put(String var1, String var2);

    void put(String var1, Enum<?> var2);

    boolean contains(String var1);

    VionObject object(String var1) throws FieldExtractionException;

    Number number(String var1) throws FieldExtractionException;

    String string(String var1) throws FieldExtractionException;

    <T extends Enum<T>> T constant(String var1, Class<T> var2) throws FieldExtractionException;

    Map<String, Object> toMap();
}
