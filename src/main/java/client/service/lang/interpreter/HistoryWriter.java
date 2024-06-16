//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter;

import client.service.lang.interpreter.exception.InterpreterException;

public final class HistoryWriter implements Interpreter {
    private final Interpreter origin;
    private ExecuteHistory history;

    public HistoryWriter(Interpreter origin, ExecuteHistory history) {
        this.origin = origin;
        this.history = history;
    }

    public void execute(Instruction instruction) throws InterpreterException {
        this.origin.execute(instruction);
        this.history.add(instruction);
    }

    public Instruction[] lastExecuted() {
        return this.history.lastNInstructions();
    }
}
