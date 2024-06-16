//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.repository.basement;

import commons.model.Entry;
import commons.model.Person;
import commons.model.Ticket;
import commons.model.TicketEntry;
import commons.model.TicketType;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import server.simple.persistence.repository.exception.CapacityExceededException;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public interface TicketRepository {
    Collection<Entry> all();

    TicketEntry insert(String var1, Ticket var2) throws EntryAlreadyExistsException, CapacityExceededException;

    void updateById(int var1, Ticket var2) throws NoSuchEntryException;

    void clear();

    void removeEntryWithKey(String var1) throws NoSuchEntryException;

    void removeAllThoseLessThan(TicketEntry var1);

    void removeAllThoseWithKeyLessThan(String var1);

    default Map<LocalDate, List<TicketEntry>> entriesGroupedByCreationDate() {
        Function<TicketEntry, LocalDate> creationDate = (entry) -> {
            Date date = entry.creationDate();
            return LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
        };
        return (Map)this.all().stream().map(Entry::ticket).collect(Collectors.groupingBy(creationDate));
    }

    int countOfEntriesWithPersonGreaterThan(Person var1);

    Collection<TicketEntry> entriesWithTypeGreaterThan(TicketType var1);
}
