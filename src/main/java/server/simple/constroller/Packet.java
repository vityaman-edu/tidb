//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.constroller;

import java.net.SocketAddress;
import java.util.Arrays;

public final class Packet {
    private final SocketAddress address;
    private final byte[] payload;

    public Packet(SocketAddress address, byte[] payload) {
        this.address = address;
        this.payload = payload;
    }

    public SocketAddress address() {
        return this.address;
    }

    public byte[] payload() {
        return this.payload;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Packet)) {
            return false;
        } else {
            Packet other = (Packet)o;
            Object this$address = this.address();
            Object other$address = other.address();
            if (this$address == null) {
                if (other$address == null) {
                    return Arrays.equals(this.payload(), other.payload());
                }
            } else if (this$address.equals(other$address)) {
                return Arrays.equals(this.payload(), other.payload());
            }

            return false;
        }
    }

    public int hashCode() {
        int result = 1;
        Object $address = this.address();
        result = result * 59 + ($address == null ? 43 : $address.hashCode());
        result = result * 59 + Arrays.hashCode(this.payload());
        return result;
    }

    public String toString() {
        return "Packet(address=" + this.address() + ", payload=" + Arrays.toString(this.payload()) + ")";
    }
}
