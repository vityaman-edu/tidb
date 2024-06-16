//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.file;

import commons.file.File;
import commons.model.TicketDataset;
import commons.model.vion.serialization.VionDatasetSerializer;
import commons.vion.MapBasedVionObject;
import commons.vion.exception.ConversionException;
import commons.vion.exception.DeserializationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public final class TicketDatasetFile implements File<TicketDataset> {
    private final JsonFile origin;
    private final VionDatasetSerializer serialization;

    public TicketDatasetFile(JsonFile origin, VionDatasetSerializer serialization) {
        this.origin = origin;
        this.serialization = serialization;
    }

    public void write(TicketDataset content) throws IOException {
        this.origin.write(this.serialization.serialize(content).toMap());
    }

    public TicketDataset content() throws IOException {
        Map<String, Object> content = this.origin.content();

        try {
            return this.serialization.deserialize(MapBasedVionObject.fromMap(content));
        } catch (DeserializationException | ConversionException var3) {
            Exception e = var3;
            throw new IOException(e);
        }
    }

    public Path path() {
        return this.origin.path();
    }
}
