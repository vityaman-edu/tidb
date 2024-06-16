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
import java.io.IOException;
import java.util.List;

public final class GroupByCreationDate implements Executable {
    private final Out out;
    private final RemoteTicketCollection tickets;

    public GroupByCreationDate(Out out, RemoteTicketCollection tickets) {
        this.out = out;
        this.tickets = tickets;
    }

    private void execute() throws IOException, AuthorizationRequiredException {
        this.tickets.entriesGroupedByCreationDate().forEach((date, tickets) -> {
            this.out.println(String.format("| date: %s, count: %s", date, tickets.size()));
            Out var10001 = this.out;
            tickets.forEach(var10001::println);
        });
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
