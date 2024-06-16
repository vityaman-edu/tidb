//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.communication.AuthorizationRequiredException;
import client.communication.RemoteTicketCollection;
import client.service.lang.interpreter.Executable;
import client.service.lang.interpreter.exception.ExecutionException;
import java.io.IOException;
import java.util.List;

public final class RemoveKeyLessThan implements Executable {
    private final RemoteTicketCollection tickets;

    public RemoveKeyLessThan(RemoteTicketCollection tickets) {
        this.tickets = tickets;
    }

    private void execute(String given) throws IOException, AuthorizationRequiredException {
        this.tickets.removeAllThoseWithKeyLessThan(given);
    }

    public void execute(List<Object> args) throws ExecutionException {
        try {
            this.execute((String)args.get(0));
        } catch (AuthorizationRequiredException | IOException var3) {
            Exception e = var3;
            throw new ExecutionException(e);
        }
    }
}
