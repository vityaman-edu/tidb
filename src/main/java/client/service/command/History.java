//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.controller.ui.out.Out;
import client.service.lang.interpreter.Executable;
import client.service.lang.interpreter.Instruction;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public final class History implements Executable {
    private final Out out;
    private final Supplier<Instruction[]> history;

    public History(Out out, Supplier<Instruction[]> history) {
        this.out = out;
        this.history = history;
    }

    private void execute() {
        Arrays.stream((Object[])this.history.get()).forEachOrdered((instruction) -> {
            this.out.println("- " + instruction);
        });
    }

    public void execute(List<Object> args) {
        this.execute();
    }
}
