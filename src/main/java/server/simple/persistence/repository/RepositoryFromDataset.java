//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.repository;

import commons.model.Entry;
import commons.model.Person;
import commons.model.Ticket;
import commons.model.TicketDataset;
import commons.model.TicketEntry;
import commons.model.TicketType;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import server.simple.persistence.repository.basement.TicketRepository;
import server.simple.persistence.repository.exception.CapacityExceededException;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public final class RepositoryFromDataset implements TicketRepository {
    private final TicketsStorage storage;

    public RepositoryFromDataset(TicketDataset tickets) {
        this.storage = new TicketsStorage(tickets.nextId(), tickets.ticketsByKey());
    }

    public TicketDataset dataset() {
        TicketDataset.Builder dataset = TicketDataset.fresh();
        dataset.withNextId(this.storage.nextId());
        Iterator var2 = this.all().iterator();

        while(var2.hasNext()) {
            Entry entry = (Entry)var2.next();
            dataset.withEntry(entry.key(), entry.ticket());
        }

        return dataset.instance();
    }

    public Collection<Entry> all() {
        return this.storage.all();
    }

    public TicketEntry insert(String key, Ticket ticket) throws EntryAlreadyExistsException, CapacityExceededException {
        return this.storage.insert(key, ticket);
    }

    public void updateById(int id, Ticket ticket) throws NoSuchEntryException {
        this.storage.updateById(id, ticket);
    }

    public void removeEntryWithKey(String key) throws NoSuchEntryException {
        this.storage.removeByKey(key);
    }

    public void clear() {
        this.removeEntriesWithKeys((Set)this.storage.all().stream().map(Entry::key).collect(Collectors.toSet()));
    }

    public void removeAllThoseLessThan(TicketEntry given) {
        this.removeEntriesWithIds(this.storage.all().stream().map(Entry::ticket).filter((e) -> {
            return e.compareTo(given) < 0;
        }).mapToInt(TicketEntry::id).toArray());
    }

    public void removeAllThoseWithKeyLessThan(String given) {
        this.removeEntriesWithKeys((Set)this.storage.all().stream().map(Entry::key).filter((k) -> {
            return k.compareTo(given) < 0;
        }).collect(Collectors.toSet()));
    }

    public Map<LocalDate, List<TicketEntry>> entriesGroupedByCreationDate() {
        Function<TicketEntry, LocalDate> creationDate = (entry) -> {
            Date date = entry.creationDate();
            return LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
        };
        return (Map)this.storage.all().stream().map(Entry::ticket).collect(Collectors.groupingBy(creationDate));
    }

    public int countOfEntriesWithPersonGreaterThan(Person given) {
        return (int)this.storage.all().stream().map(Entry::ticket).filter((entry) -> {
            return entry.person().compareTo(given) > 0;
        }).count();
    }

    public Collection<TicketEntry> entriesWithTypeGreaterThan(TicketType given) {
        return (Collection)this.storage.all().stream().map(Entry::ticket).filter((entry) -> {
            return entry.type().compareTo(given) > 0;
        }).collect(Collectors.toList());
    }

    private void removeEntriesWithKeys(Set<String> keys) {
        try {
            Iterator var2 = keys.iterator();

            while(var2.hasNext()) {
                String key = (String)var2.next();
                this.storage.removeByKey(key);
            }
        } catch (NoSuchEntryException var4) {
        }

    }

    private void removeEntriesWithIds(int[] ids) {
        try {
            int[] var2 = ids;
            int var3 = ids.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                int id = var2[var4];
                this.storage.removeById(id);
            }
        } catch (NoSuchEntryException var6) {
        }

    }
}
