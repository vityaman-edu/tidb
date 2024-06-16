//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter;

import java.util.LinkedList;
import java.util.Queue;

public final class ExecuteHistory {
    private final int limit;
    private final Queue<Instruction> queue;

    public ExecuteHistory(int size) {
        this.limit = size;
        this.queue = new LinkedList();
    }

    public void add(Instruction e) {
        if (this.queue.size() >= this.limit) {
            this.queue.poll();
        }

        this.queue.add(e);
    }

    public Instruction[] lastNInstructions() {
        return (Instruction[])this.queue.toArray(new Instruction[0]);
    }
}
