//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.communication.AuthorizationRequiredException;
import client.communication.RemoteTicketCollection;
import client.controller.ui.input.Input;
import client.controller.ui.input.RequestObject;
import client.controller.ui.out.Out;
import client.service.lang.interpreter.Executable;
import client.service.lang.interpreter.exception.ExecutionException;
import java.io.IOException;
import java.util.List;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public final class UpdateByIdInteractive implements Executable {
    private final RequestObject request;
    private final RemoteTicketCollection tickets;

    public UpdateByIdInteractive(Input in, Out out, RemoteTicketCollection tickets) {
        this.request = new RequestObject(in, out);
        this.tickets = tickets;
    }

    private void execute(int id) throws NoSuchEntryException, IOException, AuthorizationRequiredException {
        this.tickets.status();
        this.tickets.updateById(id, this.request.ticket());
    }

    public void execute(List<Object> args) throws ExecutionException {
        try {
            this.execute((Integer)args.get(0));
        } catch (IOException | AuthorizationRequiredException | NoSuchEntryException var3) {
            Exception e = var3;
            throw new ExecutionException(e);
        }
    }
}
