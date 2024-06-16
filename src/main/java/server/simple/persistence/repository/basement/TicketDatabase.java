//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.repository.basement;

import commons.model.Ticket;
import commons.model.TicketEntry;
import java.util.Optional;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public interface TicketDatabase {
    void removeById(int var1) throws NoSuchEntryException;

    Optional<TicketEntry> entryWithId(int var1);

    void updateById(int var1, Ticket var2) throws NoSuchEntryException;
}
