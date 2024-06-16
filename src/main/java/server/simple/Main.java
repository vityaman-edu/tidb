//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple;

import commons.model.vion.serialization.VionCoordinatesSerializer;
import commons.model.vion.serialization.VionDatasetSerializer;
import commons.model.vion.serialization.VionLocationSerializer;
import commons.model.vion.serialization.VionPersonSerializer;
import commons.model.vion.serialization.VionTicketEntrySerializer;
import commons.model.vion.serialization.VionTicketSerializer;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import server.simple.constroller.CommunicationServer;
import server.simple.persistence.file.JsonFile;
import server.simple.persistence.file.TicketDatasetFile;
import server.simple.persistence.repository.TicketsFromFile;
import server.simple.persistence.repository.basement.TicketRepository;
import server.simple.service.MethodExecutor;

public final class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Serializable var10000 = new Serializable() {
            private int a;

            public int hashCode() {
                return super.hashCode();
            }
        };

        try {
            Configuration configuration = Configuration.load(Paths.get("res", "server-config.yml"));
            TicketsFromFile tickets = new TicketsFromFile(new TicketDatasetFile(new JsonFile(Paths.get("res", "collection.json")), new VionDatasetSerializer(new VionTicketEntrySerializer(new VionTicketSerializer(new VionCoordinatesSerializer(), new VionPersonSerializer(new VionLocationSerializer())), new SimpleDateFormat("dd.MM.yyyy hh:mm:ss")))));
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    tickets.save();
                } catch (IOException var2) {
                    IOException e = var2;
                    throw new RuntimeException(e);
                }
            }));
            TicketRepository repository = tickets.repository();
            MethodExecutor executor = new MethodExecutor(repository);
            CommunicationServer server = new CommunicationServer(configuration.connection().address(), executor);
            server.run();
        } catch (IOException | Configuration.ReadingException var7) {
            Exception e = var7;
            throw new RuntimeException(e);
        }
    }
}
