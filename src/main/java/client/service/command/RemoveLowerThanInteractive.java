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
import commons.model.Ticket;
import commons.model.TicketEntry;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public final class RemoveLowerThanInteractive implements Executable {
    private final RequestObject request;
    private final RemoteTicketCollection tickets;

    public RemoveLowerThanInteractive(Input in, Out out, RemoteTicketCollection tickets) {
        this.request = new RequestObject(in, out);
        this.tickets = tickets;
    }

    public void execute() throws IOException, AuthorizationRequiredException {
        this.tickets.status();
        Ticket given = this.request.ticket();
        this.tickets.removeAllThoseLessThan(new TicketEntry(0, new Date(), given));
    }

    public void execute(List<Object> args) throws ExecutionException {
        try {
            this.execute();
        } catch (AuthorizationRequiredException | IOException var3) {
            Exception e = var3;
            throw new ExecutionException(e);
        }
    }
}
