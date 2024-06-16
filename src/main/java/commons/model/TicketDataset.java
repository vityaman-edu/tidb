//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model;

import commons.validation.UntrustedObject;
import commons.validation.ValidationResult;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class TicketDataset implements UntrustedObject {
    private final int nextId;
    private final Map<String, TicketEntry> ticketsByKey;

    public TicketDataset() {
        this.nextId = 1;
        this.ticketsByKey = new HashMap();
    }

    public int nextId() {
        return this.nextId;
    }

    public Map<String, TicketEntry> ticketsByKey() {
        return Collections.unmodifiableMap(this.ticketsByKey);
    }

    public static Builder fresh() {
        return new Builder();
    }

    public ValidationResult validate() {
        ValidationResult.Builder<TicketDataset> result = ValidationResult.of("TicketDataset", this);
        Iterator var2 = this.ticketsByKey.values().iterator();

        while(var2.hasNext()) {
            TicketEntry entry = (TicketEntry)var2.next();
            result.gathering(entry::validate);
        }

        return result.verdict();
    }

    private TicketDataset(Builder builder) {
        this.nextId = builder.nextId;
        this.ticketsByKey = builder.ticketsById;
    }

    public String toString() {
        return "TicketDataset(nextId=" + this.nextId() + ", ticketsByKey=" + this.ticketsByKey() + ")";
    }

    public static final class Builder {
        private Integer nextId;
        private final Map<String, TicketEntry> ticketsById = new HashMap();

        public Builder() {
        }

        public Builder withNextId(int nextId) {
            if (nextId <= 0) {
                throw new IllegalArgumentException(String.format("nextId must be positive, not %s", nextId));
            } else {
                this.nextId = nextId;
                return this;
            }
        }

        public Builder withEntry(String key, TicketEntry entry) {
            this.ticketsById.put(key, entry);
            return this;
        }

        public TicketDataset instance() {
            if (this.nextId == null) {
                throw new IllegalStateException(String.format("nextId: %s", this.nextId));
            } else {
                return new TicketDataset(this);
            }
        }
    }
}
