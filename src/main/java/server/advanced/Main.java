//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced;

import commons.protocol.low.Request;
import commons.protocol.low.Response;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import server.advanced.controller.Packet;
import server.advanced.controller.high.UserAuthController;
import server.advanced.controller.low.PacketDeserializer;
import server.advanced.controller.low.PacketSerializer;
import server.advanced.controller.low.UDPEndpoint;
import server.advanced.persistence.JdbcAuthService;
import server.advanced.persistence.JdbcHeliosConnection;

public final class Main {
    public Main() {
    }

    public static void main(String[] args) throws Configuration.ReadingException, IOException {
        Configuration config = Configuration.load(Paths.get("res", "advanced-server-config.yml"));
        BlockingQueue<Packet<byte[]>> channelToDeserializerQueue = new LinkedBlockingDeque(3 * config.threads().deserialization());
        BlockingQueue<Packet<Request>> deserializerToHandlerQueue = new LinkedBlockingDeque(3 * config.threads().service());
        BlockingQueue<Packet<Response>> handlerToSerializerQueue = new LinkedBlockingDeque(3 * config.threads().serialization());
        UDPEndpoint endpoint = new UDPEndpoint(config.connection().address(), (p) -> {
            sneakyPut(channelToDeserializerQueue, p);
        });
        PacketDeserializer deserializer = new PacketDeserializer(() -> {
            return (Packet)sneakyTake(channelToDeserializerQueue);
        }, (p) -> {
            sneakyPut(deserializerToHandlerQueue, p);
        }, Executors.newFixedThreadPool(config.threads().deserialization()));
        UserAuthController controller = new UserAuthController(new JdbcAuthService(JdbcHeliosConnection.openRemoteUsingSSH(config.database().username(), config.database().password())), () -> {
            return (Packet)sneakyTake(deserializerToHandlerQueue);
        }, (p) -> {
            sneakyPut(handlerToSerializerQueue, p);
        }, Executors.newFixedThreadPool(config.threads().service()));
        PacketSerializer serializer = new PacketSerializer(() -> {
            return (Packet)sneakyTake(handlerToSerializerQueue);
        }, endpoint, Executors.newCachedThreadPool());
        Executor app = Executors.newFixedThreadPool(5);
        app.execute(endpoint);
        app.execute(deserializer);
        app.execute(controller);
        app.execute(serializer);
        app.execute(() -> {
            while(true) {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException var4) {
                    InterruptedException e = var4;
                    e.printStackTrace();
                }

                System.out.println("channelToDeserializerQueue.size() = " + channelToDeserializerQueue.size());
                System.out.println("deserializerToHandlerQueue.size() = " + deserializerToHandlerQueue.size());
                System.out.println("handlerToSerializerQueue.size() = " + handlerToSerializerQueue.size());
            }
        });
    }

    private static <T> void sneakyPut(BlockingQueue<T> queue, T item) {
        try {
            queue.put(item);
        } catch (InterruptedException var3) {
            InterruptedException e = var3;
            throw new IllegalStateException(e);
        }
    }

    private static <T> T sneakyTake(BlockingQueue<T> queue) {
        try {
            return queue.take();
        } catch (InterruptedException var2) {
            InterruptedException e = var2;
            throw new IllegalStateException(e);
        }
    }
}
