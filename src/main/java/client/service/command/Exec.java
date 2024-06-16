//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.controller.ui.out.Out;
import client.service.lang.Program;
import client.service.lang.interpreter.Executable;
import client.service.lang.interpreter.Instruction;
import client.service.lang.interpreter.Interpreter;
import client.service.lang.interpreter.exception.ExecutionException;
import client.service.lang.interpreter.exception.InterpreterException;
import commons.vion.exception.UncheckedParseException;
import commons.vion.parsing.utils.LineByLineSequence;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import server.simple.persistence.file.FileLines;

public final class Exec implements Executable {
    private final Interpreter interpreter;
    private final Out out;

    public Exec(Out out, Interpreter interpreter) {
        this.out = out;
        this.interpreter = interpreter;
    }

    public void execute(String scriptpath) throws ExecutionException {
        Path path = Paths.get(scriptpath);

        try {
            FileLines lines = new FileLines(path);
            Throwable var4 = null;

            try {
                Program program = new Program(new LineByLineSequence(lines));
                Iterator var6 = program.iterator();

                while(var6.hasNext()) {
                    Instruction instruction = (Instruction)var6.next();

                    try {
                        this.interpreter.execute(instruction);
                    } catch (InterpreterException var18) {
                        InterpreterException e = var18;
                        this.out.error(path + ": runtime error: " + e.getMessage());
                    }
                }
            } catch (Throwable var19) {
                var4 = var19;
                throw var19;
            } finally {
                if (lines != null) {
                    if (var4 != null) {
                        try {
                            lines.close();
                        } catch (Throwable var17) {
                            var4.addSuppressed(var17);
                        }
                    } else {
                        lines.close();
                    }
                }

            }

        } catch (ParseException | UncheckedParseException | IOException var21) {
            Exception e = var21;
            throw new ExecutionException(e);
        }
    }

    public void execute(List<Object> args) throws ExecutionException {
        this.execute((String)args.get(0));
    }
}
