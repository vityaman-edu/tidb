//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter.exception;

import client.service.lang.interpreter.Instruction;
import java.util.Collection;

public class RecursiveCallException extends ExecutionException {
    public RecursiveCallException(Instruction instruction, Collection<Instruction> callStackFrame) {
        super(String.format("Recursive call of instruction %s, callStack: %s", instruction, callStackFrame));
    }
}
