//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.controller.ui.InteractiveConsole;
import client.controller.ui.input.StreamInput;
import client.controller.ui.out.Out;
import client.service.CommandInterpreter;
import client.service.lang.interpreter.Executable;
import client.service.lang.interpreter.exception.ExecutionException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class ExecScript implements Executable {
    private final Out out;
    private final CommandInterpreter interpreter;

    public ExecScript(Out out, CommandInterpreter interpreter) {
        this.out = out;
        this.interpreter = interpreter;
    }

    public void execute(String filepath) throws FileNotFoundException, ExecutionException {
        Path path = Paths.get(filepath);
        if (this.interpreter.trace().contains(path)) {
            throw new ExecutionException(String.format("Recursion call of %s, trace: %s", path, this.interpreter.trace()));
        } else {
            this.interpreter.trace().add(path);
            InteractiveConsole console = new InteractiveConsole(new StreamInput(new FileInputStream(path.toFile())), this.out, this.interpreter);
            console.run();
            this.interpreter.trace().remove(path);
        }
    }

    public void execute(List<Object> args) throws ExecutionException {
        try {
            this.execute((String)args.get(0));
        } catch (FileNotFoundException var3) {
            FileNotFoundException e = var3;
            throw new ExecutionException(e);
        }
    }
}
