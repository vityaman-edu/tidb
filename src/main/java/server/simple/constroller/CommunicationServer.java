//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.constroller;

import commons.protocol.low.Request;
import commons.protocol.low.Response;
import commons.protocol.method.MethodInvocation;
import commons.protocol.method.MethodResult;
import commons.validation.ValidationResult;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationException;
import server.simple.service.MethodExecutor;

public final class CommunicationServer implements PacketReceiver, Runnable {
    private static final Logger log = Logger.getLogger(CommunicationServer.class.getName());
    private final LowLevelEndpoint endpoint;
    private final MethodExecutor executor;

    public CommunicationServer(InetSocketAddress address, MethodExecutor executor) {
        this.executor = executor;
        this.endpoint = new LowLevelEndpoint(address);
        this.endpoint.addPacketReceiver(this);
    }

    public void run() {
        log.info("Server is started!");
        this.endpoint.run();
    }

    public void receivePacket(Packet packet) {
        try {
            ReceivedRequest request = new ReceivedRequest(packet.address(), Request.fromBytes(packet.payload()));
            SocketAddress sender = packet.address();
            ValidationResult validation = request.validate();
            log.info(String.format("Received request %s from %s (%s)", sender, request, validation.message()));
            if (validation.succeed()) {
                MethodInvocation<?> invocation = (MethodInvocation)request.payloadAs(MethodInvocation.class);
                Iterator var6 = this.executor.execute(invocation).split().iterator();

                while(var6.hasNext()) {
                    MethodResult result = (MethodResult)var6.next();
                    Response response = new Response(request.id(), result);
                    log.info("Send response: " + response);
                    this.endpoint.send(new Packet(sender, response.asByteArray()));
                }
            }
        } catch (ClassCastException | SerializationException var9) {
            log.info(String.format("Invalid packet received from %s", packet.address()));
        }

    }
}
