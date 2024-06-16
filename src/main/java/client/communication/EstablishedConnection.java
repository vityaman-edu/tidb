//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.communication;

import commons.protocol.low.Request;
import commons.protocol.low.Response;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;

public interface EstablishedConnection extends Closeable {
    InetAddress partner();

    Response receive() throws IOException;

    void send(Request var1) throws IOException;
}
