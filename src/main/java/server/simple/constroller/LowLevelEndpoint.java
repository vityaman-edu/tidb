//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.constroller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public final class LowLevelEndpoint implements PacketManager {
    private final InetSocketAddress address;
    private final Set<PacketReceiver> packetReceivers;
    private final Queue<Packet> responsesToSend;
    private ByteBuffer buffer;
    private boolean running;

    LowLevelEndpoint(InetSocketAddress address) {
        this.address = address;
        this.packetReceivers = new HashSet();
        this.running = false;
        this.responsesToSend = new LinkedList();
    }

    public void send(Packet packet) {
        this.responsesToSend.add(packet);
    }

    public void addPacketReceiver(PacketReceiver receiver) {
        this.packetReceivers.add(receiver);
    }

    public void run() {
        this.buffer = ByteBuffer.allocate(65536);
        this.running = true;

        while(this.running) {
            try {
                DatagramChannel channel = DatagramChannel.open().bind(this.address);
                Throwable var2 = null;

                try {
                    while(this.running) {
                        SocketAddress sender = this.receiveBytesFrom(channel);
                        this.handleBytesReceivedFrom(sender);
                        this.sendAvailablePackets(channel);
                    }
                } catch (Throwable var12) {
                    var2 = var12;
                    throw var12;
                } finally {
                    if (channel != null) {
                        if (var2 != null) {
                            try {
                                channel.close();
                            } catch (Throwable var11) {
                                var2.addSuppressed(var11);
                            }
                        } else {
                            channel.close();
                        }
                    }

                }
            } catch (IOException var14) {
                IOException e = var14;
                throw new RuntimeException(e);
            }
        }

    }

    private SocketAddress receiveBytesFrom(DatagramChannel channel) throws IOException {
        this.buffer.clear();
        SocketAddress sender = channel.receive(this.buffer);
        this.buffer.flip();
        return sender;
    }

    private void handleBytesReceivedFrom(SocketAddress sender) {
        byte[] bytes = new byte[this.buffer.remaining()];
        this.buffer.get(bytes);
        this.packetReceivers.forEach((e) -> {
            e.receivePacket(new Packet(sender, bytes));
        });
    }

    private void sendAvailablePackets(DatagramChannel channel) throws IOException {
        while(!this.responsesToSend.isEmpty()) {
            Packet packet = (Packet)this.responsesToSend.poll();
            channel.send(ByteBuffer.wrap(packet.payload()), packet.address());
        }

    }
}
