//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced;

public abstract class Worker implements Runnable {
    private volatile boolean running = false;

    public Worker() {
    }

    public void run() {
        this.running = true;

        while(this.running) {
            this.action();
        }

    }

    public void stop() {
        this.running = false;
    }

    protected abstract void action();
}
