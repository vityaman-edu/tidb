//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.controller.ui.out.ConsoleColor;
import client.controller.ui.out.Out;
import client.service.lang.interpreter.Executable;
import java.util.List;

public final class Pwd implements Executable {
    private final Out out;

    public Pwd(Out out) {
        this.out = out;
    }

    private void execute() {
        this.out.println(ConsoleColor.GREEN.wrapped(System.getProperty("user.dir")));
    }

    public void execute(List<Object> args) {
        this.execute();
    }
}
