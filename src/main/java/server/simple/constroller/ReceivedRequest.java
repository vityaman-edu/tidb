//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.constroller;

import commons.protocol.low.Request;
import java.net.SocketAddress;

public final class ReceivedRequest extends Request {
    private final SocketAddress sender;

    public ReceivedRequest(SocketAddress sender, Request request) {
        super(request.id(), request.payload());
        this.sender = sender;
    }

    public String toString() {
        return "ReceivedRequest(sender=" + this.sender() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ReceivedRequest)) {
            return false;
        } else {
            ReceivedRequest other = (ReceivedRequest)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                Object this$sender = this.sender();
                Object other$sender = other.sender();
                if (this$sender == null) {
                    if (other$sender != null) {
                        return false;
                    }
                } else if (!this$sender.equals(other$sender)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ReceivedRequest;
    }

    public int hashCode() {
        int result = super.hashCode();
        Object $sender = this.sender();
        result = result * 59 + ($sender == null ? 43 : $sender.hashCode());
        return result;
    }

    public SocketAddress sender() {
        return this.sender;
    }
}
