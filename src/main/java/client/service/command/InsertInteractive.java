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
import java.util.List;
import server.simple.persistence.repository.exception.CollectionException;

public final class InsertInteractive implements Executable {
    private final RequestObject request;
    private final Out out;
    private final RemoteTicketCollection tickets;

    public InsertInteractive(Input in, Out out, RemoteTicketCollection tickets) {
        this.request = new RequestObject(in, out);
        this.out = out;
        this.tickets = tickets;
    }

    private void execute(String key) throws CollectionException, IOException, AuthorizationRequiredException {
        this.tickets.status();
        Ticket ticket = this.request.ticket();
        TicketEntry entry = this.tickets.insert(key, ticket);
        this.out.println(String.format("New ticket added with id: %s and creationDate: %s", entry.id(), entry.creationDate()));
    }

    public void execute(List<Object> args) throws ExecutionException {
        try {
            this.execute((String)args.get(0));
        } catch (IOException | AuthorizationRequiredException | CollectionException var3) {
            Exception e = var3;
            throw new ExecutionException(e);
        }
    }
}
