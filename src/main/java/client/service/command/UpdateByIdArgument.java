//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.communication.AuthorizationRequiredException;
import client.communication.RemoteTicketCollection;
import client.service.lang.interpreter.Executable;
import client.service.lang.interpreter.exception.ExecutionException;
import commons.model.Ticket;
import commons.model.vion.serialization.VionTicketSerializer;
import commons.validation.ValidationResult;
import commons.vion.VionObject;
import commons.vion.exception.DeserializationException;
import java.io.IOException;
import java.util.List;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public final class UpdateByIdArgument implements Executable {
    private final RemoteTicketCollection tickets;
    private final VionTicketSerializer serialization;

    public UpdateByIdArgument(RemoteTicketCollection tickets, VionTicketSerializer serialization) {
        this.tickets = tickets;
        this.serialization = serialization;
    }

    private void execute(int id, Ticket ticket) throws NoSuchEntryException, IOException, ExecutionException, AuthorizationRequiredException {
        ValidationResult validation = ticket.validate();
        if (!validation.succeed()) {
            throw new ExecutionException(validation.message());
        } else {
            this.tickets.updateById(id, ticket);
        }
    }

    public void execute(List<Object> args) throws ExecutionException {
        try {
            this.execute((Integer)args.get(0), this.serialization.deserialize((VionObject)args.get(1)));
        } catch (IOException | NoSuchEntryException | AuthorizationRequiredException | DeserializationException var3) {
            Exception e = var3;
            throw new ExecutionException(e);
        }
    }
}
