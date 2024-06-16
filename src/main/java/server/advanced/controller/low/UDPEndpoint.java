//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.controller.low;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import java.util.logging.Logger;
import server.advanced.Worker;
import server.advanced.controller.Packet;

public final class UDPEndpoint extends Worker implements AutoCloseable, Consumer<Packet<byte[]>> {
    private static final Logger log = Logger.getLogger(UDPEndpoint.class.getName());
    private final Consumer<Packet<byte[]>> packetConsumer;
    private final DatagramChannel channel;
    private final ByteBuffer receiveBuffer;

    public UDPEndpoint(InetSocketAddress address, Consumer<Packet<byte[]>> packetConsumer) throws IOException {
        this.packetConsumer = packetConsumer;
        this.channel = DatagramChannel.open().bind(address);
        log.info(String.format("DatagramChannel opened on %s.", this.channel.getLocalAddress()));
        this.receiveBuffer = ByteBuffer.allocate(4096);
    }

    protected void action() {
        try {
            this.packetConsumer.accept(this.receivePacket());
        } catch (IOException var2) {
            IOException e = var2;
            throw new UncheckedIOException(e);
        }
    }

    public void run() {
        log.info("UDP Endpoint started.");
        super.run();
    }

    private Packet<byte[]> receivePacket() throws IOException {
        this.receiveBuffer.clear();
        SocketAddress sender = this.channel.receive(this.receiveBuffer);
        this.receiveBuffer.flip();
        byte[] bytes = new byte[this.receiveBuffer.remaining()];
        this.receiveBuffer.get(bytes);
        Packet<byte[]> packet = new Packet(sender, bytes);
        log.info(String.format("Received bytes from %s: %s.", packet.address(), new String((byte[])packet.payload(), StandardCharsets.US_ASCII)));
        return packet;
    }

    public void send(byte[] bytes, SocketAddress dest) throws IOException {
        this.channel.send(ByteBuffer.wrap(bytes), dest);
    }

    public void accept(Packet<byte[]> packet) {
        try {
            this.send((byte[])packet.payload(), packet.address());
        } catch (IOException var3) {
            IOException e = var3;
            throw new UncheckedIOException(e);
        }
    }

    public void close() throws IOException {
        this.stop();
        this.channel.disconnect();
        this.channel.close();
        log.info("UDP endpoint stopped.");
    }
}
