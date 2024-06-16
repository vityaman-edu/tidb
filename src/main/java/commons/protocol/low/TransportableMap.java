//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.low;

import commons.validation.ValidationResult;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class TransportableMap<K extends Serializable, V extends TransportableObject> extends HashMap<K, V> implements TransportableObject {
    public TransportableMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    public TransportableMap() {
    }

    public ValidationResult validate() {
        ValidationResult.Builder<TransportableMap<K, V>> validation = ValidationResult.of("TransportableMap", this);

        TransportableObject value;
        for(Iterator var2 = this.values().iterator(); var2.hasNext(); validation = validation.gathering(value::validate)) {
            value = (TransportableObject)var2.next();
            value.getClass();
        }

        return validation.verdict();
    }
}
