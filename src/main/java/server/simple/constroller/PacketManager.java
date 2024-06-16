//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.constroller;

public interface PacketManager extends Runnable {
    void send(Packet var1);

    void addPacketReceiver(PacketReceiver var1);
}
