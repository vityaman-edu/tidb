//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.constroller;

import commons.protocol.low.Response;
import java.net.SocketAddress;

public final class ResponseToSend {
    private final SocketAddress receiver;
    private final Response response;

    public ResponseToSend(SocketAddress receiver, Response response) {
        this.receiver = receiver;
        this.response = response;
    }

    public SocketAddress receiver() {
        return this.receiver;
    }

    public Response response() {
        return this.response;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseToSend)) {
            return false;
        } else {
            ResponseToSend other = (ResponseToSend)o;
            Object this$receiver = this.receiver();
            Object other$receiver = other.receiver();
            if (this$receiver == null) {
                if (other$receiver != null) {
                    return false;
                }
            } else if (!this$receiver.equals(other$receiver)) {
                return false;
            }

            Object this$response = this.response();
            Object other$response = other.response();
            if (this$response == null) {
                if (other$response != null) {
                    return false;
                }
            } else if (!this$response.equals(other$response)) {
                return false;
            }

            return true;
        }
    }

    public int hashCode() {
        int result = 1;
        Object $receiver = this.receiver();
        result = result * 59 + ($receiver == null ? 43 : $receiver.hashCode());
        Object $response = this.response();
        result = result * 59 + ($response == null ? 43 : $response.hashCode());
        return result;
    }

    public String toString() {
        return "ResponseToSend(receiver=" + this.receiver() + ", response=" + this.response() + ")";
    }
}
