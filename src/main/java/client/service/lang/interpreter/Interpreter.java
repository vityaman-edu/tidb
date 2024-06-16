//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter;

import client.service.lang.interpreter.exception.InterpreterException;

public interface Interpreter {
    void execute(Instruction var1) throws InterpreterException;
}
