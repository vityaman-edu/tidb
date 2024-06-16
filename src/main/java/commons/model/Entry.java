//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model;

import commons.protocol.low.TransportableObject;
import commons.validation.ValidationResult;
import java.util.Objects;

public final class Entry implements TransportableObject {
    private final String key;
    private final TicketEntry ticket;

    public ValidationResult validate() {
        ValidationResult.Builder var10000 = ValidationResult.of("entry", this).checking(Objects::nonNull, "key must be not null");
        TicketEntry var10001 = this.ticket;
        var10001.getClass();
        return var10000.gathering(var10001::validate).verdict();
    }

    public Entry(String key, TicketEntry ticket) {
        this.key = key;
        this.ticket = ticket;
    }

    public String key() {
        return this.key;
    }

    public TicketEntry ticket() {
        return this.ticket;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Entry)) {
            return false;
        } else {
            Entry other = (Entry)o;
            Object this$key = this.key();
            Object other$key = other.key();
            if (this$key == null) {
                if (other$key != null) {
                    return false;
                }
            } else if (!this$key.equals(other$key)) {
                return false;
            }

            Object this$ticket = this.ticket();
            Object other$ticket = other.ticket();
            if (this$ticket == null) {
                if (other$ticket != null) {
                    return false;
                }
            } else if (!this$ticket.equals(other$ticket)) {
                return false;
            }

            return true;
        }
    }

    public int hashCode() {
        int result = 1;
        Object $key = this.key();
        result = result * 59 + ($key == null ? 43 : $key.hashCode());
        Object $ticket = this.ticket();
        result = result * 59 + ($ticket == null ? 43 : $ticket.hashCode());
        return result;
    }

    public String toString() {
        return "Entry(key=" + this.key() + ", ticket=" + this.ticket() + ")";
    }
}
