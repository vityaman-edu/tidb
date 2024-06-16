//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.low;

import commons.validation.ValidationResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public final class TransportableList<T extends TransportableObject> extends ArrayList<T> implements TransportableObject {
    public TransportableList() {
    }

    public TransportableList(Collection<T> collection) {
        super(collection);
    }

    public TransportableList(int initialCapacity) {
        super(initialCapacity);
    }

    public ValidationResult validate() {
        ValidationResult.Builder<TransportableList<T>> validation = ValidationResult.of("list", this);
        Iterator<T> var2 = this.iterator();

        while(var2.hasNext()) {
            T element = var2.next();
            validation.gathering(element::validate);
        }

        return validation.verdict();
    }
}
