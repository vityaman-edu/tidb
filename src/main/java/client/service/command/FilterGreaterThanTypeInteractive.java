//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.communication.AuthorizationRequiredException;
import client.communication.RemoteTicketCollection;
import client.controller.ui.input.Input;
import client.controller.ui.input.RequestPrimitive;
import client.controller.ui.out.Out;
import client.service.lang.interpreter.Executable;
import client.service.lang.interpreter.exception.ExecutionException;
import commons.model.TicketType;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public final class FilterGreaterThanTypeInteractive implements Executable {
    private final RequestPrimitive primitive;
    private final Out out;
    private final RemoteTicketCollection tickets;

    public FilterGreaterThanTypeInteractive(Input in, Out out, RemoteTicketCollection tickets) {
        this.primitive = new RequestPrimitive(in, out);
        this.out = out;
        this.tickets = tickets;
    }

    private void execute() throws IOException, AuthorizationRequiredException {
        TicketType given = (TicketType)this.primitive.request(RequestPrimitive.enumFor("type", TicketType.class));
        Collection var10000 = this.tickets.entriesWithTypeGreaterThan(given);
        Out var10001 = this.out;
        var10000.forEach(var10001::println);
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
