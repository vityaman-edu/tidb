//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.controller;

import java.net.SocketAddress;

public final class Packet<T> {
    private final SocketAddress address;
    private final T payload;

    public Packet(SocketAddress address, T payload) {
        this.address = address;
        this.payload = payload;
    }

    public SocketAddress address() {
        return this.address;
    }

    public T payload() {
        return this.payload;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Packet)) {
            return false;
        } else {
            Packet<?> other = (Packet)o;
            Object this$address = this.address();
            Object other$address = other.address();
            if (this$address == null) {
                if (other$address != null) {
                    return false;
                }
            } else if (!this$address.equals(other$address)) {
                return false;
            }

            Object this$payload = this.payload();
            Object other$payload = other.payload();
            if (this$payload == null) {
                if (other$payload != null) {
                    return false;
                }
            } else if (!this$payload.equals(other$payload)) {
                return false;
            }

            return true;
        }
    }

    public int hashCode() {
        int result = 1;
        Object $address = this.address();
        result = result * 59 + ($address == null ? 43 : $address.hashCode());
        Object $payload = this.payload();
        result = result * 59 + ($payload == null ? 43 : $payload.hashCode());
        return result;
    }

    public String toString() {
        return "Packet(address=" + this.address() + ", payload=" + this.payload() + ")";
    }
}
