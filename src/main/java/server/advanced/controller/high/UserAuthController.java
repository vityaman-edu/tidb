//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.controller.high;

import commons.protocol.low.Request;
import commons.protocol.low.Response;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;
import server.advanced.Worker;
import server.advanced.controller.Packet;
import server.advanced.persistence.AuthService;

public final class UserAuthController extends Worker {
    private static final Logger log = Logger.getLogger(UserAuthController.class.getName());
    private final AuthService service;
    private final Supplier<Packet<Request>> requestSupplier;
    private final Consumer<Packet<Response>> responseConsumer;
    private final Map<SocketAddress, Consumer<Request>> requestHandlerByClient;
    private final ExecutorService executorService;

    public UserAuthController(AuthService authService, Supplier<Packet<Request>> requestSupplier, Consumer<Packet<Response>> responseSupplier, ExecutorService executorService) {
        this.service = authService;
        this.requestSupplier = requestSupplier;
        this.responseConsumer = responseSupplier;
        this.executorService = executorService;
        this.requestHandlerByClient = new HashMap();
    }

    protected void action() {
        Packet<Request> packet = (Packet)this.requestSupplier.get();
        Consumer<Request> requestHandler = (Consumer)this.requestHandlerByClient.computeIfAbsent(packet.address(), (k) -> {
            return new RequestHandler(packet.address(), this.service, this.responseConsumer);
        });
        this.executorService.execute(() -> {
            requestHandler.accept(packet.payload());
        });
    }

    public void run() {
        log.info("Service имени Егора Бугаенко started.");
        super.run();
    }

    public void stop() {
        super.stop();
        log.info("Service во имя Егора Бугаенко завершается.");
    }
}
