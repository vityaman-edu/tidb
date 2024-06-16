//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter;

import client.service.lang.interpreter.exception.InterpreterException;
import client.service.lang.interpreter.exception.RecursiveCallException;
import java.util.ArrayDeque;
import java.util.Deque;

public final class RecursionControlInterpreter implements Interpreter {
    private final Interpreter origin;
    private final Deque<Instruction> callStack = new ArrayDeque();

    public RecursionControlInterpreter(Interpreter origin) {
        this.origin = origin;
    }

    public void execute(Instruction instruction) throws InterpreterException {
        if (this.callStack.contains(instruction)) {
            throw new RecursiveCallException(instruction, this.callStack);
        } else {
            this.callStack.addLast(instruction);

            try {
                this.origin.execute(instruction);
            } catch (InterpreterException var6) {
                InterpreterException e = var6;
                throw new InterpreterException(e);
            } finally {
                this.callStack.removeLast();
            }

        }
    }
}
