//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence;

import java.sql.Connection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import server.advanced.persistence.CachedTicketRepository;
import server.advanced.persistence.JdbcTicketRepository;
import server.simple.persistence.repository.basement.TicketRepository;

public final class TicketRepositoryFactory {
    private final ConcurrentMap<String, TicketRepository> repoByUsername;
    private final Connection connection;

    public TicketRepositoryFactory(Connection connection) {
        this.connection = connection;
        this.repoByUsername = new ConcurrentHashMap();
    }

    public TicketRepository repositoryForUser(String username) {
        return (TicketRepository)this.repoByUsername.computeIfAbsent(username, (k) -> {
            return new CachedTicketRepository(new JdbcTicketRepository(username, this.connection));
        });
    }
}
