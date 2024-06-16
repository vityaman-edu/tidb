//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.repository;

import commons.file.File;
import commons.model.TicketDataset;
import java.io.IOException;
import server.simple.persistence.repository.basement.TicketRepository;

public final class TicketsFromFile {
    private final File<TicketDataset> file;
    private final RepositoryFromDataset collection;

    public TicketsFromFile(File<TicketDataset> file) throws IOException {
        this.file = file;
        this.collection = new RepositoryFromDataset((TicketDataset)file.content());
    }

    public TicketRepository repository() {
        return this.collection;
    }

    public void save() throws IOException {
        this.file.write(this.collection.dataset());
    }
}
