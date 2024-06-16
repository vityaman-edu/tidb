//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.service;

import commons.model.Entry;
import commons.model.TicketEntry;
import commons.protocol.low.TransportableList;
import commons.protocol.low.TransportableMap;
import commons.protocol.method.AllMethod;
import commons.protocol.method.ClearMethod;
import commons.protocol.method.CountGreaterPersonMethod;
import commons.protocol.method.EntriesGreaterTypeMethod;
import commons.protocol.method.GroupedByCreationDateMethod;
import commons.protocol.method.InsertMethod;
import commons.protocol.method.MethodInvocation;
import commons.protocol.method.MethodResult;
import commons.protocol.method.RemoveEntryByKeyMethod;
import commons.protocol.method.RemoveLowerKeyMethod;
import commons.protocol.method.RemoveLowerMethod;
import commons.protocol.method.Status;
import commons.protocol.method.StatusMethod;
import commons.protocol.method.UpdateByIdMethod;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import server.simple.persistence.repository.basement.TicketRepository;
import server.simple.persistence.repository.exception.CapacityExceededException;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public final class MethodExecutor {
    private static final Logger log = Logger.getLogger(MethodExecutor.class.getName());
    private final TicketRepository repository;
    private final Map<Class<? extends MethodInvocation<?>>, Function<MethodInvocation<?>, MethodResult>> resultByInvocation;

    public MethodExecutor(TicketRepository repository) {
        this.repository = repository;
        this.resultByInvocation = new HashMap<Class<? extends MethodInvocation<?>>, Function<MethodInvocation<?>, MethodResult>>() {
            {
                this.put(StatusMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.status(i);
                });
                this.put(AllMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.all(i);
                });
                this.put(InsertMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.insert(i);
                });
                this.put(RemoveEntryByKeyMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.removeEntryWithKey(i);
                });
                this.put(UpdateByIdMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.updateById(i);
                });
                this.put(ClearMethod.Invocation.class, (x$0) -> {
                    return MethodExecutor.this.clear(x$0);
                });
                this.put(RemoveLowerMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.removeAllThoseLessThan(i);
                });
                this.put(RemoveLowerKeyMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.removeAllThoseWithKeyLessThan(i);
                });
                this.put(GroupedByCreationDateMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.entriesGroupedByCreationDate(i);
                });
                this.put(CountGreaterPersonMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.countOfEntriesWithPersonGreaterThan(i);
                });
                this.put(EntriesGreaterTypeMethod.Invocation.class, (i) -> {
                    return MethodExecutor.this.entriesWithTypeGreaterThan(i);
                });
            }
        };
    }

    public MethodResult execute(MethodInvocation<?> invocation) {
        log.info(String.format("Invoke method %s", invocation));
        Function<MethodInvocation<?>, MethodResult> result = (Function)this.resultByInvocation.get(invocation.getClass());
        if (result == null) {
            throw new IllegalStateException("All possible method invocations must be handled");
        } else {
            return (MethodResult)result.apply(invocation);
        }
    }

    private MethodResult status(MethodInvocation<?> invocation) {
        return new StatusMethod.Result(Status.OK, "Server is alive");
    }

    private MethodResult all(MethodInvocation<?> invocation) {
        TransportableList<Entry> entries = new TransportableList(this.repository.all());
        return new AllMethod.Result(entries, entries.size());
    }

    private MethodResult updateById(MethodInvocation<?> invocation) {
        UpdateByIdMethod.Invocation arg = (UpdateByIdMethod.Invocation)invocation;

        try {
            this.repository.updateById(arg.id(), arg.ticket());
        } catch (NoSuchEntryException var4) {
            return new UpdateByIdMethod.Result(Status.NO_SUCH_ENTRY);
        }

        return new UpdateByIdMethod.Result(Status.OK);
    }

    private MethodResult clear(MethodInvocation<?> invocation) {
        ClearMethod.Invocation arg = (ClearMethod.Invocation)invocation;
        this.repository.clear();
        return new ClearMethod.Result(Status.OK);
    }

    private MethodResult insert(MethodInvocation<?> invocation) {
        InsertMethod.Invocation arg = (InsertMethod.Invocation)invocation;

        try {
            TicketEntry inserted = this.repository.insert(arg.key(), arg.ticket());
            return new InsertMethod.Result(inserted);
        } catch (CapacityExceededException var4) {
            return new InsertMethod.Result(Status.CAPACITY_EXCEEDED);
        } catch (EntryAlreadyExistsException var5) {
            return new InsertMethod.Result(Status.ENTRY_ALREADY_EXISTS);
        }
    }

    private MethodResult removeEntryWithKey(MethodInvocation<?> invocation) {
        RemoveEntryByKeyMethod.Invocation arg = (RemoveEntryByKeyMethod.Invocation)invocation;

        try {
            this.repository.removeEntryWithKey(arg.key());
        } catch (NoSuchEntryException var4) {
            return new RemoveEntryByKeyMethod.Result(Status.NO_SUCH_ENTRY);
        }

        return new RemoveEntryByKeyMethod.Result(Status.OK);
    }

    private MethodResult removeAllThoseLessThan(MethodInvocation<?> invocation) {
        RemoveLowerMethod.Invocation arg = (RemoveLowerMethod.Invocation)invocation;
        this.repository.removeAllThoseLessThan(arg.sample());
        return new RemoveLowerMethod.Result(Status.OK);
    }

    private MethodResult removeAllThoseWithKeyLessThan(MethodInvocation<?> invocation) {
        RemoveLowerKeyMethod.Invocation arg = (RemoveLowerKeyMethod.Invocation)invocation;
        this.repository.removeAllThoseWithKeyLessThan(arg.key());
        return new RemoveLowerKeyMethod.Result(Status.OK);
    }

    private MethodResult entriesGroupedByCreationDate(MethodInvocation<?> invocation) {
        GroupedByCreationDateMethod.Invocation arg = (GroupedByCreationDateMethod.Invocation)invocation;
        Map<LocalDate, List<TicketEntry>> result = this.repository.entriesGroupedByCreationDate();
        TransportableMap<LocalDate, TransportableList<TicketEntry>> map = new TransportableMap();
        Iterator var5 = result.entrySet().iterator();

        while(var5.hasNext()) {
            Map.Entry<LocalDate, List<TicketEntry>> entry = (Map.Entry)var5.next();
            map.put(entry.getKey(), new TransportableList((Collection)entry.getValue()));
        }

        return new GroupedByCreationDateMethod.Result(map);
    }

    private MethodResult countOfEntriesWithPersonGreaterThan(MethodInvocation<?> invocation) {
        CountGreaterPersonMethod.Invocation arg = (CountGreaterPersonMethod.Invocation)invocation;
        int count = this.repository.countOfEntriesWithPersonGreaterThan(arg.sample());
        return new CountGreaterPersonMethod.Result(count);
    }

    private MethodResult entriesWithTypeGreaterThan(MethodInvocation<?> invocation) {
        EntriesGreaterTypeMethod.Invocation arg = (EntriesGreaterTypeMethod.Invocation)invocation;
        Collection<TicketEntry> entries = this.repository.entriesWithTypeGreaterThan(arg.type());
        return new EntriesGreaterTypeMethod.Result(new TransportableList(entries));
    }
}
