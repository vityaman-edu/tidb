//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.controller.low;

import commons.protocol.low.Request;
import commons.protocol.method.MethodInvocation;
import commons.validation.ValidationResult;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationException;
import server.advanced.Worker;
import server.advanced.controller.Packet;

public final class PacketDeserializer extends Worker {
    private static final Logger log = Logger.getLogger(PacketDeserializer.class.getName());
    private final Supplier<Packet<byte[]>> byteSupplier;
    private final Consumer<Packet<Request>> requestConsumer;
    private final ExecutorService executorService;

    public PacketDeserializer(Supplier<Packet<byte[]>> byteSupplier, Consumer<Packet<Request>> requestConsumer, ExecutorService executorService) {
        this.byteSupplier = byteSupplier;
        this.requestConsumer = requestConsumer;
        this.executorService = executorService;
    }

    protected void action() {
        Packet<byte[]> bytes = (Packet)this.byteSupplier.get();
        this.executorService.execute(() -> {
            SocketAddress sender = bytes.address();

            try {
                Request request = Request.fromBytes((byte[])bytes.payload());
                Packet<Request> packet = new Packet(sender, request);
                ValidationResult validation = request.validate();
                log.info(String.format("Received request %s from %s (%s).", sender, request, validation.message()));
                if (validation.succeed() && request.payload() instanceof MethodInvocation) {
                    this.requestConsumer.accept(packet);
                }
            } catch (ClassCastException | SerializationException var6) {
                log.info(String.format("Invalid packet received from %s", sender));
            }

        });
    }

    public void run() {
        log.info("Packet Deserializer started.");
        super.run();
    }

    public void stop() {
        super.stop();
        log.info("Packet Deserializer stopped.");
    }
}
