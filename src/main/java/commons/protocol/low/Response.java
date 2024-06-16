//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.low;

import commons.validation.ValidationResult;
import org.apache.commons.lang3.SerializationUtils;

public final class Response implements ByteFormTransportable {
    private final int requestId;
    private final TransportableObject payload;

    public <T> T payload(Class<T> type) {
        return type.cast(this.payload);
    }

    public ValidationResult validate() {
        ValidationResult.Builder var10000 = ValidationResult.of("response", this).checking((v) -> {
            return v.requestId > 0;
        }, "requestId must be in [1, MAX_INT]");
        TransportableObject var10001 = this.payload;
        var10001.getClass();
        return var10000.gathering(var10001::validate).verdict();
    }

    public static Response fromBytes(byte[] bytes) {
        return (Response)SerializationUtils.deserialize(bytes);
    }

    public Response(int requestId, TransportableObject payload) {
        this.requestId = requestId;
        this.payload = payload;
    }

    public int requestId() {
        return this.requestId;
    }

    public TransportableObject payload() {
        return this.payload;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Response)) {
            return false;
        } else {
            Response other = (Response)o;
            if (this.requestId() != other.requestId()) {
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

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.requestId();
        Object $payload = this.payload();
        result = result * 59 + ($payload == null ? 43 : $payload.hashCode());
        return result;
    }

    public String toString() {
        return "Response(requestId=" + this.requestId() + ", payload=" + this.payload() + ")";
    }
}
