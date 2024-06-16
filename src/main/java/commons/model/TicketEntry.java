//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model;

import commons.protocol.low.TransportableObject;
import java.util.Date;

public final class TicketEntry extends Ticket implements Comparable<TicketEntry>, TransportableObject {
    private final int id;
    private final Date creationDate;

    public TicketEntry(int id, Date creationDate, Ticket ticket) {
        super(ticket);
        this.id = id;
        this.creationDate = creationDate;
    }

    public TicketEntry withDataUpdated(Ticket data) {
        return new TicketEntry(this.id, this.creationDate, data);
    }

    public int compareTo(TicketEntry ticketEntry) {
        return (int)Math.signum((float)(this.id - ticketEntry.id));
    }

    public String toString() {
        return "TicketEntry(super=" + super.toString() + ", id=" + this.id() + ", creationDate=" + this.creationDate() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TicketEntry)) {
            return false;
        } else {
            TicketEntry other = (TicketEntry)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else if (this.id() != other.id()) {
                return false;
            } else {
                Object this$creationDate = this.creationDate();
                Object other$creationDate = other.creationDate();
                if (this$creationDate == null) {
                    if (other$creationDate == null) {
                        return true;
                    }
                } else if (this$creationDate.equals(other$creationDate)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof TicketEntry;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = result * 59 + this.id();
        Object $creationDate = this.creationDate();
        result = result * 59 + ($creationDate == null ? 43 : $creationDate.hashCode());
        return result;
    }

    public int id() {
        return this.id;
    }

    public Date creationDate() {
        return this.creationDate;
    }
}
