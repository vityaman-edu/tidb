//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter;

import client.service.lang.interpreter.exception.InterpreterException;
import client.service.lang.interpreter.exception.NoSuchInstructionException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class SimpleInterpreter implements Interpreter {
    private final Map<String, Map<Signature, Executable>> commandBySignatureByKeyword = new HashMap();

    public SimpleInterpreter(Command... commands) {
        Command[] var2 = commands;
        int var3 = commands.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Command cmd = var2[var4];
            this.load(cmd);
        }

    }

    public void execute(Instruction instruction) throws InterpreterException {
        this.commandBySignature(instruction.signature()).execute(instruction.arguments());
    }

    private Executable commandBySignature(Signature signature) throws InterpreterException {
        Map<Signature, Executable> commands = (Map)this.commandBySignatureByKeyword.get(signature.name());
        if (commands == null) {
            throw new NoSuchInstructionException(signature.name());
        } else {
            Iterator var3 = commands.keySet().iterator();

            Signature original;
            do {
                if (!var3.hasNext()) {
                    throw new NoSuchInstructionException(signature.toString());
                }

                original = (Signature)var3.next();
            } while(!signature.fitsTo(original));

            return (Executable)commands.get(original);
        }
    }

    public void load(Command command) {
        ((Map)this.commandBySignatureByKeyword.computeIfAbsent(command.signature().name(), (k) -> {
            return new HashMap();
        })).put(command.signature(), command.executable());
    }
}
