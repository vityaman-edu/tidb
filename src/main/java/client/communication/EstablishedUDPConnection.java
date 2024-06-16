//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.communication;

import commons.protocol.low.Request;
import commons.protocol.low.Response;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import org.apache.commons.lang3.SerializationUtils;

public final class EstablishedUDPConnection implements EstablishedConnection {
    private static final int DEFAULT_BUFFER_CAPACITY = 100000;
    private final byte[] buffer;
    private final DatagramSocket socket = new DatagramSocket();
    private final List<Response> receivedResponses = new LinkedList();
    private int expectedResponseId = 0;

    public EstablishedUDPConnection(SocketAddress partner, int timeout, int bufferCapacity) throws SocketException {
        this.socket.connect(partner);
        this.socket.setSoTimeout(timeout);
        this.buffer = new byte[bufferCapacity];
    }

    public InetAddress partner() {
        return this.socket.getInetAddress();
    }

    public Response receive() throws IOException {
        while(true) {
            if (this.expectedResponseId != 0) {
                Optional<Response> response = this.responseForRequestWithId(this.expectedResponseId);
                if (response.isPresent()) {
                    return (Response)response.get();
                }
            }

            DatagramPacket packet = new DatagramPacket(this.buffer, this.buffer.length);
            this.socket.receive(packet);
            this.receivedResponses.add(Response.fromBytes(packet.getData()));
        }
    }

    public void send(Request request) throws IOException {
        DatagramPacket packet = new DatagramPacket(new byte[0], 0);
        packet.setData(SerializationUtils.serialize(request));
        this.socket.send(packet);
        this.expectedResponseId = request.id();
    }

    public void close() {
        this.socket.disconnect();
        this.socket.close();
    }

    private Optional<Response> responseForRequestWithId(int requestId) {
        ListIterator<Response> it = this.receivedResponses.listIterator();

        Response resp;
        do {
            if (!it.hasNext()) {
                return Optional.empty();
            }

            resp = (Response)it.next();
        } while(resp.requestId() != requestId);

        it.remove();
        return Optional.of(resp);
    }
}
