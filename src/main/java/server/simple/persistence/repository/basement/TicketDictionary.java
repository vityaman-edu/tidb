//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.repository.basement;

import commons.model.Entry;
import commons.model.Ticket;
import commons.model.TicketEntry;
import java.util.Optional;
import java.util.Set;
import server.simple.persistence.repository.exception.CapacityExceededException;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public interface TicketDictionary {
    void removeByKey(String var1) throws NoSuchEntryException;

    Optional<TicketEntry> entryWithKey(String var1) throws NoSuchEntryException;

    void updateByKey(String var1, Ticket var2) throws NoSuchEntryException;

    TicketEntry insert(String var1, Ticket var2) throws EntryAlreadyExistsException, CapacityExceededException;

    Set<Entry> all();
}
