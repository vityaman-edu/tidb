//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.persistence;

import commons.model.Entry;
import commons.model.Person;
import commons.model.Ticket;
import commons.model.TicketEntry;
import commons.model.TicketType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import server.simple.persistence.repository.basement.TicketRepository;
import server.simple.persistence.repository.exception.CapacityExceededException;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public class CachedTicketRepository implements TicketRepository {
    private final TicketRepository origin;
    private final ConcurrentMap<String, TicketEntry> ticketsByKey;

    public CachedTicketRepository(TicketRepository origin) {
        this.origin = origin;
        this.ticketsByKey = new ConcurrentHashMap((Map)origin.all().stream().collect(Collectors.toMap(Entry::key, Entry::ticket)));
    }

    public Collection<Entry> all() {
        return (Collection)this.ticketsByKey.entrySet().stream().map((e) -> {
            return new Entry((String)e.getKey(), (TicketEntry)e.getValue());
        }).collect(Collectors.toList());
    }

    public synchronized TicketEntry insert(String key, Ticket ticket) throws EntryAlreadyExistsException, CapacityExceededException {
        if (this.ticketsByKey.containsKey(key)) {
            throw EntryAlreadyExistsException.withKey(key);
        } else {
            TicketEntry inserted = this.origin.insert(key, ticket);
            this.ticketsByKey.put(key, inserted);
            return inserted;
        }
    }

    public synchronized void updateById(int id, Ticket ticket) throws NoSuchEntryException {
        Optional<Entry> entryWithGivenId = this.ticketsByKey.entrySet().stream().filter((e) -> {
            return ((TicketEntry)e.getValue()).id() == id;
        }).map((e) -> {
            return new Entry((String)e.getKey(), (TicketEntry)e.getValue());
        }).findAny();
        if (!entryWithGivenId.isPresent()) {
            throw NoSuchEntryException.withId(id);
        } else {
            Entry entry = (Entry)entryWithGivenId.get();
            this.ticketsByKey.put(entry.key(), entry.ticket().withDataUpdated(ticket));
            this.origin.updateById(id, ticket);
        }
    }

    public synchronized void clear() {
        this.ticketsByKey.clear();
        this.origin.clear();
    }

    public synchronized void removeEntryWithKey(String key) throws NoSuchEntryException {
        if (!this.ticketsByKey.containsKey(key)) {
            throw NoSuchEntryException.withKey(key);
        } else {
            this.ticketsByKey.remove(key);
            this.origin.removeEntryWithKey(key);
        }
    }

    public synchronized void removeAllThoseLessThan(TicketEntry entry) {
        List<String> keys = (List)this.ticketsByKey.entrySet().stream().filter((e) -> {
            return ((TicketEntry)e.getValue()).id() < entry.id();
        }).map(Map.Entry::getKey).collect(Collectors.toList());
        Iterator var3 = keys.iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            this.ticketsByKey.remove(key);
        }

        this.origin.removeAllThoseLessThan(entry);
    }

    public synchronized void removeAllThoseWithKeyLessThan(String key) {
        List<String> keys = (List)this.ticketsByKey.keySet().stream().filter((kx) -> {
            return kx.compareTo(key) < 0;
        }).collect(Collectors.toList());
        Iterator var3 = keys.iterator();

        while(var3.hasNext()) {
            String k = (String)var3.next();
            this.ticketsByKey.remove(k);
        }

        this.origin.removeAllThoseWithKeyLessThan(key);
    }

    public int countOfEntriesWithPersonGreaterThan(Person person) {
        return (int)this.ticketsByKey.values().stream().map(Ticket::person).filter((e) -> {
            return e.height() < person.height();
        }).count();
    }

    public Collection<TicketEntry> entriesWithTypeGreaterThan(TicketType type) {
        return (Collection)this.ticketsByKey.values().stream().filter((e) -> {
            return e.type().compareTo(type) > 0;
        }).collect(Collectors.toList());
    }
}
