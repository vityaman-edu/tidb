//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.communication;

import commons.model.Entry;
import commons.model.Person;
import commons.model.Ticket;
import commons.model.TicketEntry;
import commons.model.TicketType;
import commons.protocol.low.Request;
import commons.protocol.low.Response;
import commons.protocol.method.AllMethod;
import commons.protocol.method.ClearMethod;
import commons.protocol.method.CountGreaterPersonMethod;
import commons.protocol.method.EntriesGreaterTypeMethod;
import commons.protocol.method.GroupedByCreationDateMethod;
import commons.protocol.method.InsertMethod;
import commons.protocol.method.LoginMethod;
import commons.protocol.method.MethodInvocation;
import commons.protocol.method.MethodResult;
import commons.protocol.method.RegisterMethod;
import commons.protocol.method.RemoveEntryByKeyMethod;
import commons.protocol.method.RemoveLowerKeyMethod;
import commons.protocol.method.RemoveLowerMethod;
import commons.protocol.method.Status;
import commons.protocol.method.StatusMethod;
import commons.protocol.method.UpdateByIdMethod;
import commons.validation.ValidationResult;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import server.simple.persistence.repository.exception.CapacityExceededException;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public final class TicketServerConnection implements RemoteTicketCollection {
    private final EstablishedConnection connection;
    private int nextRequestId = 1;

    public TicketServerConnection(EstablishedConnection connection) {
        this.connection = connection;
    }

    public String register(String username, String password) throws IOException {
        try {
            return ((RegisterMethod.Result)this.remoteMethodInvoke(new RegisterMethod.Invocation(username, password))).status().toString();
        } catch (AuthorizationRequiredException var4) {
            AuthorizationRequiredException e = var4;
            throw new AssertionError(e.getMessage());
        }
    }

    public String login(String username, String password) throws IOException {
        try {
            return ((LoginMethod.Result)this.remoteMethodInvoke(new LoginMethod.Invocation(username, password))).status().toString();
        } catch (AuthorizationRequiredException var4) {
            AuthorizationRequiredException e = var4;
            throw new AssertionError(e.getMessage());
        }
    }

    public String status() throws IOException {
        try {
            return ((StatusMethod.Result)this.remoteMethodInvoke(new StatusMethod.Invocation())).message();
        } catch (AuthorizationRequiredException var2) {
            AuthorizationRequiredException e = var2;
            throw new AssertionError(e.getMessage());
        }
    }

    public Collection<Entry> all() throws IOException, AuthorizationRequiredException {
        this.sendRequestFor(new AllMethod.Invocation());
        LinkedList<Entry> entries = new LinkedList();

        AllMethod.Result result;
        do {
            result = (AllMethod.Result)this.receiveResult(AllMethod.Result.class);
            entries.addAll(result.entries());
        } while(result.remaining() != 0);

        return entries;
    }

    public TicketEntry insert(String key, Ticket ticket) throws EntryAlreadyExistsException, CapacityExceededException, IOException, AuthorizationRequiredException {
        InsertMethod.Result result = (InsertMethod.Result)this.remoteMethodInvoke(new InsertMethod.Invocation(key, ticket));
        switch (result.status()) {
            case ENTRY_ALREADY_EXISTS:
                throw EntryAlreadyExistsException.withKey(key);
            case CAPACITY_EXCEEDED:
                throw new CapacityExceededException("Storage is full");
            default:
                return result.insertedEntry();
        }
    }

    public void updateById(int id, Ticket ticket) throws NoSuchEntryException, IOException, AuthorizationRequiredException {
        UpdateByIdMethod.Result result = (UpdateByIdMethod.Result)this.remoteMethodInvoke(new UpdateByIdMethod.Invocation(id, ticket));
        switch (result.status()) {
            case NO_SUCH_ENTRY:
                throw NoSuchEntryException.withId(id);
            default:
        }
    }

    public void clear() throws IOException, AuthorizationRequiredException {
        this.remoteMethodInvoke(new ClearMethod.Invocation());
    }

    public void removeEntryWithKey(String key) throws NoSuchEntryException, IOException, AuthorizationRequiredException {
        RemoveEntryByKeyMethod.Result result = (RemoveEntryByKeyMethod.Result)this.remoteMethodInvoke(new RemoveEntryByKeyMethod.Invocation(key));
        if (result.status() == Status.NO_SUCH_ENTRY) {
            throw NoSuchEntryException.withKey(key);
        }
    }

    public void removeAllThoseLessThan(TicketEntry sample) throws IOException, AuthorizationRequiredException {
        this.remoteMethodInvoke(new RemoveLowerMethod.Invocation(sample));
    }

    public void removeAllThoseWithKeyLessThan(String sample) throws IOException, AuthorizationRequiredException {
        this.remoteMethodInvoke(new RemoveLowerKeyMethod.Invocation(sample));
    }

    public Map<LocalDate, ? extends List<TicketEntry>> entriesGroupedByCreationDate() throws IOException, AuthorizationRequiredException {
        return ((GroupedByCreationDateMethod.Result)this.remoteMethodInvoke(new GroupedByCreationDateMethod.Invocation())).entriesGroupedByCreationDate();
    }

    public int countOfEntriesWithPersonGreaterThan(Person sample) throws IOException, AuthorizationRequiredException {
        return ((CountGreaterPersonMethod.Result)this.remoteMethodInvoke(new CountGreaterPersonMethod.Invocation(sample))).count();
    }

    public Collection<TicketEntry> entriesWithTypeGreaterThan(TicketType type) throws IOException, AuthorizationRequiredException {
        return ((EntriesGreaterTypeMethod.Result)this.remoteMethodInvoke(new EntriesGreaterTypeMethod.Invocation(type))).entries();
    }

    private <R extends MethodResult> R remoteMethodInvoke(MethodInvocation<R> invocation) throws IOException, AuthorizationRequiredException {
        this.sendRequestFor(invocation);
        R result = this.receiveResult(invocation.resultType());
        if (result.status() == Status.NOT_AUTHORIZED) {
            throw new AuthorizationRequiredException("Access denied");
        } else {
            return result;
        }
    }

    private void sendRequestFor(MethodInvocation<?> invocation) throws IOException {
        this.connection.send(new Request(this.nextRequestId++, invocation));
    }

    private <T extends MethodResult> T receiveResult(Class<T> type) throws IOException, AuthorizationRequiredException {
        Response response = this.connection.receive();
        ValidationResult validation = response.validate();
        if (!validation.succeed()) {
            throw new IOException("bad response " + validation.message());
        } else {
            try {
                T result = response.payload(type);
                if (result.status().equals(Status.NOT_AUTHORIZED)) {
                    throw new AuthorizationRequiredException("Not authorized");
                } else {
                    return result;
                }
            } catch (ClassCastException var5) {
                throw new IOException("bad response");
            }
        }
    }
}
