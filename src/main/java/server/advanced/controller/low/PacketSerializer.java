//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.controller.low;

import commons.protocol.low.Response;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;
import server.advanced.Worker;
import server.advanced.controller.Packet;

public final class PacketSerializer extends Worker {
    private static final Logger log = Logger.getLogger(PacketSerializer.class.getName());
    private final Supplier<Packet<Response>> responseSupplier;
    private final Consumer<Packet<byte[]>> byteConsumer;
    private final ExecutorService executorService;

    public PacketSerializer(Supplier<Packet<Response>> responseSupplier, Consumer<Packet<byte[]>> byteConsumer, ExecutorService executorService) {
        this.responseSupplier = responseSupplier;
        this.byteConsumer = byteConsumer;
        this.executorService = executorService;
    }

    protected void action() {
        Packet<Response> responsePacket = (Packet)this.responseSupplier.get();
        log.info(String.format("serializing of packet %s started", responsePacket));
        this.executorService.execute(() -> {
            this.byteConsumer.accept(new Packet(responsePacket.address(), ((Response)responsePacket.payload()).asByteArray()));
        });
    }

    public void run() {
        log.info("Packet Serializer-Sender started.");
        super.run();
    }

    public void stop() {
        log.info("Packet Serializer-Sender stopped.");
        super.stop();
    }
}
