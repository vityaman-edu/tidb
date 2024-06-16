//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.repository;

import commons.model.Entry;
import commons.model.Ticket;
import commons.model.TicketEntry;
import java.time.Clock;
import java.util.AbstractSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import server.simple.persistence.repository.basement.TicketDictionary;
import server.simple.persistence.repository.exception.CapacityExceededException;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public class MapTicketsDictionary implements TicketDictionary {
    private final Clock clock;
    private int nextId;
    private final Map<String, TicketEntry> entriesByKey;

    public MapTicketsDictionary(int nextId, Map<String, TicketEntry> entriesByKey, Clock clock) {
        this.nextId = nextId;
        this.entriesByKey = new HashMap(entriesByKey);
        this.clock = clock;
    }

    public MapTicketsDictionary(int nextId, Map<String, TicketEntry> entriesByKey) {
        this(nextId, entriesByKey, Clock.systemUTC());
    }

    int nextId() {
        return this.nextId;
    }

    public void removeByKey(String key) throws NoSuchEntryException {
        TicketEntry deletedEntry = (TicketEntry)this.entriesByKey.remove(key);
        if (deletedEntry == null) {
            throw NoSuchEntryException.withKey(key);
        }
    }

    public final Optional<TicketEntry> entryWithKey(String key) {
        return Optional.ofNullable(this.entriesByKey.get(key));
    }

    public final void updateByKey(String key, Ticket ticket) throws NoSuchEntryException {
        Optional<TicketEntry> entry = this.entryWithKey(key);
        if (!entry.isPresent()) {
            throw NoSuchEntryException.withKey(key);
        } else {
            this.entriesByKey.put(key, ((TicketEntry)entry.get()).withDataUpdated(ticket));
        }
    }

    public TicketEntry insert(String key, Ticket ticket) throws EntryAlreadyExistsException, CapacityExceededException {
        if (this.entriesByKey.containsKey(key)) {
            throw EntryAlreadyExistsException.withKey(key);
        } else {
            int id = this.nextId++;
            if (id <= 0) {
                --this.nextId;
                throw new CapacityExceededException("Collection capacity exceeded");
            } else {
                TicketEntry entryToBeInserted = new TicketEntry(id, Date.from(this.clock.instant()), ticket);
                this.entriesByKey.put(key, entryToBeInserted);
                return entryToBeInserted;
            }
        }
    }

    public final Set<Entry> all() {
        return new AbstractSet<Entry>() {
            final Set<Map.Entry<String, TicketEntry>> entries;

            {
                this.entries = MapTicketsDictionary.this.entriesByKey.entrySet();
            }

            public Iterator<Entry> iterator() {
                return new Iterator<Entry>() {
                    final Iterator<Map.Entry<String, TicketEntry>> origin;

                    {
                        this.origin = entries.iterator();
                    }

                    public boolean hasNext() {
                        return this.origin.hasNext();
                    }

                    public Entry next() {
                        Map.Entry<String, TicketEntry> entry = (Map.Entry)this.origin.next();
                        return new Entry((String)entry.getKey(), (TicketEntry)entry.getValue());
                    }
                };
            }

            public int size() {
                return this.entries.size();
            }
        };
    }
}
