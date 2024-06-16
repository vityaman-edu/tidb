//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.repository;

import commons.model.Entry;
import commons.model.Ticket;
import commons.model.TicketEntry;
import java.time.Clock;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import server.simple.persistence.repository.basement.TicketDatabase;
import server.simple.persistence.repository.exception.CapacityExceededException;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public final class TicketsStorage extends MapTicketsDictionary implements TicketDatabase {
    private final Map<Integer, String> keysById;

    public TicketsStorage(int nextId, Map<String, TicketEntry> entriesByKey) {
        this(nextId, entriesByKey, Clock.systemUTC());
    }

    public TicketsStorage(int nextId, Map<String, TicketEntry> entriesByKey, Clock clock) {
        super(nextId, entriesByKey, clock);
        this.keysById = (Map)this.all().stream().collect(Collectors.toMap((entry) -> {
            return entry.ticket().id();
        }, Entry::key));
    }

    public void removeByKey(String key) throws NoSuchEntryException {
        this.keysById.remove(((TicketEntry)this.entryWithKey(key).orElseThrow(() -> {
            return NoSuchEntryException.withKey(key);
        })).id());
        super.removeByKey(key);
    }

    public TicketEntry insert(String key, Ticket ticket) throws EntryAlreadyExistsException, CapacityExceededException {
        TicketEntry inserted = super.insert(key, ticket);
        this.keysById.put(inserted.id(), key);
        return inserted;
    }

    public void removeById(int id) throws NoSuchEntryException {
        this.removeByKey((String)this.keyAssociatedWithId(id).orElseThrow(() -> {
            return NoSuchEntryException.withId(id);
        }));
    }

    public Optional<TicketEntry> entryWithId(int id) {
        return this.entryWithKey((String)this.keyAssociatedWithId(id).orElse(""));
    }

    public void updateById(int id, Ticket ticket) throws NoSuchEntryException {
        this.updateByKey((String)this.keyAssociatedWithId(id).orElseThrow(() -> {
            return NoSuchEntryException.withId(id);
        }), ticket);
    }

    private Optional<String> keyAssociatedWithId(int id) {
        return Optional.ofNullable(this.keysById.get(id));
    }
}
