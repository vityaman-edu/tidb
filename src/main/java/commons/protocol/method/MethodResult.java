//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.protocol.low.TransportableObject;
import commons.validation.ValidationResult;
import java.util.Collections;

public class MethodResult implements TransportableObject {
    private final Status status;

    public MethodResult(Status status) {
        this.status = status;
    }

    public Iterable<MethodResult> split() {
        return Collections.singleton(this);
    }

    public ValidationResult validate() {
        return ValidationResult.of("MethodResult", this).checking((t) -> {
            return t.status != null;
        }, "status is null").verdict();
    }

    public Status status() {
        return this.status;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MethodResult)) {
            return false;
        } else {
            MethodResult other = (MethodResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$status = this.status();
                Object other$status = other.status();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof MethodResult;
    }

    public int hashCode() {
        int result = 1;
        Object $status = this.status();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    public String toString() {
        return "MethodResult(status=" + this.status() + ")";
    }
}
