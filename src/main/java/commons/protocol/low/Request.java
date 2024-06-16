//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.low;

import commons.validation.ValidationResult;
import org.apache.commons.lang3.SerializationUtils;

public class Request implements ByteFormTransportable {
    private final int id;
    private final TransportableObject payload;

    public <T> T payloadAs(Class<T> type) {
        return type.cast(this.payload);
    }

    public ValidationResult validate() {
        ValidationResult.Builder var10000 = ValidationResult.of("request", this).checking((v) -> {
            return v.id > 0;
        }, "id must be in [1, MAX_INT]");
        TransportableObject var10001 = this.payload;
        var10001.getClass();
        return var10000.gathering(var10001::validate).verdict();
    }

    public static Request fromBytes(byte[] bytes) {
        return (Request)SerializationUtils.deserialize(bytes);
    }

    public Request(int id, TransportableObject payload) {
        this.id = id;
        this.payload = payload;
    }

    public int id() {
        return this.id;
    }

    public TransportableObject payload() {
        return this.payload;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Request)) {
            return false;
        } else {
            Request other = (Request)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.id() != other.id()) {
                return false;
            } else {
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
    }

    protected boolean canEqual(Object other) {
        return other instanceof Request;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.id();
        Object $payload = this.payload();
        result = result * 59 + ($payload == null ? 43 : $payload.hashCode());
        return result;
    }

    public String toString() {
        return "Request(id=" + this.id() + ", payload=" + this.payload() + ")";
    }
}
