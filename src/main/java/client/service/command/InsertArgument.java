//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.communication.AuthorizationRequiredException;
import client.communication.RemoteTicketCollection;
import client.controller.ui.out.Out;
import client.service.lang.interpreter.Executable;
import client.service.lang.interpreter.exception.ExecutionException;
import commons.model.Ticket;
import commons.model.TicketEntry;
import commons.model.vion.serialization.VionTicketSerializer;
import commons.validation.ValidationResult;
import commons.vion.VionObject;
import commons.vion.exception.DeserializationException;
import java.io.IOException;
import java.util.List;
import server.simple.persistence.repository.exception.CollectionException;

public final class InsertArgument implements Executable {
    private final Out out;
    private final RemoteTicketCollection tickets;
    private final VionTicketSerializer serialization;

    public InsertArgument(Out out, RemoteTicketCollection tickets, VionTicketSerializer serialization) {
        this.out = out;
        this.tickets = tickets;
        this.serialization = serialization;
    }

    private void execute(String key, Ticket ticket) throws CollectionException, IOException, ExecutionException, AuthorizationRequiredException {
        ValidationResult validation = ticket.validate();
        if (!validation.succeed()) {
            throw new ExecutionException(validation.message());
        } else {
            TicketEntry inserted = this.tickets.insert(key, ticket);
            this.out.println(String.format("New ticket added id: %s, creationDate: %s", inserted.id(), inserted.creationDate()));
        }
    }

    public void execute(List<Object> args) throws ExecutionException {
        try {
            this.execute((String)args.get(0), this.serialization.deserialize((VionObject)args.get(1)));
        } catch (IOException | CollectionException | AuthorizationRequiredException | DeserializationException var3) {
            Exception e = var3;
            throw new ExecutionException(e);
        }
    }
}
