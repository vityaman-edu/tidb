//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.low;

import org.apache.commons.lang3.SerializationUtils;

public interface ByteFormTransportable extends TransportableObject {
    default byte[] asByteArray() {
        return SerializationUtils.serialize(this);
    }
}
