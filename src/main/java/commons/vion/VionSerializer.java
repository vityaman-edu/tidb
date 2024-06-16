//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion;

import commons.vion.exception.DeserializationException;

public interface VionSerializer<T> {
    VionObject serialize(T var1);

    T deserialize(VionObject var1) throws DeserializationException;
}
