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
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import server.simple.persistence.repository.exception.CapacityExceededException;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public interface RemoteTicketCollection {
    String register(String var1, String var2) throws IOException;

    String login(String var1, String var2) throws IOException;

    String status() throws IOException;

    Collection<Entry> all() throws IOException, AuthorizationRequiredException;

    TicketEntry insert(String var1, Ticket var2) throws EntryAlreadyExistsException, CapacityExceededException, IOException, AuthorizationRequiredException;

    void updateById(int var1, Ticket var2) throws NoSuchEntryException, IOException, AuthorizationRequiredException;

    void clear() throws IOException, AuthorizationRequiredException;

    void removeEntryWithKey(String var1) throws NoSuchEntryException, IOException, AuthorizationRequiredException;

    void removeAllThoseLessThan(TicketEntry var1) throws IOException, AuthorizationRequiredException;

    void removeAllThoseWithKeyLessThan(String var1) throws IOException, AuthorizationRequiredException;

    Map<LocalDate, ? extends List<TicketEntry>> entriesGroupedByCreationDate() throws IOException, AuthorizationRequiredException;

    int countOfEntriesWithPersonGreaterThan(Person var1) throws IOException, AuthorizationRequiredException;

    Collection<TicketEntry> entriesWithTypeGreaterThan(TicketType var1) throws IOException, AuthorizationRequiredException;
}
