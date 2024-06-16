//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.service.lang.interpreter.Executable;
import java.util.List;

public final class Exit implements Executable {
    public Exit() {
    }

    public void execute() {
        System.exit(0);
    }

    public void execute(List<Object> args) {
        this.execute();
    }
}
