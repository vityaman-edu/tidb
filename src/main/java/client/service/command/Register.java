//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.communication.RemoteTicketCollection;
import client.controller.ui.out.Out;
import client.service.lang.interpreter.Executable;
import client.service.lang.interpreter.exception.ExecutionException;
import java.io.IOException;
import java.util.List;

public final class Register implements Executable {
    private final RemoteTicketCollection connection;
    private final Out out;

    public Register(RemoteTicketCollection connection, Out out) {
        this.connection = connection;
        this.out = out;
    }

    private void execute(String username, String password) throws IOException, ExecutionException {
        if (this.checkRange(username.length(), 5, 16) && this.checkRange(password.length(), 5, 16)) {
            this.out.println(this.connection.register(username, password));
        } else {
            throw new ExecutionException("empty password or username");
        }
    }

    public void execute(List<Object> args) throws ExecutionException {
        try {
            this.execute((String)args.get(0), (String)args.get(1));
        } catch (IOException var3) {
            IOException e = var3;
            throw new ExecutionException(e);
        }
    }

    private boolean checkRange(int i, int lo, int hi) {
        return lo <= i && i < hi;
    }
}
