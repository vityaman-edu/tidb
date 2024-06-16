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
import commons.model.Person;
import java.io.IOException;
import java.util.List;

public final class CountGreaterThanPersonInteractive implements Executable {
    private final RequestObject request;
    private final Out out;
    private final RemoteTicketCollection tickets;

    public CountGreaterThanPersonInteractive(Input in, Out out, RemoteTicketCollection tickets) {
        this.request = new RequestObject(in, out);
        this.out = out;
        this.tickets = tickets;
    }

    public void execute() throws IOException, AuthorizationRequiredException {
        this.tickets.status();
        Person given = this.request.person();
        this.out.println("Count: " + this.tickets.countOfEntriesWithPersonGreaterThan(given));
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
