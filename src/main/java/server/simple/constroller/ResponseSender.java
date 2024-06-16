//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.constroller;

import commons.protocol.low.Response;
import java.net.SocketAddress;

public interface ResponseSender {
    void send(Response var1, SocketAddress var2);
}
