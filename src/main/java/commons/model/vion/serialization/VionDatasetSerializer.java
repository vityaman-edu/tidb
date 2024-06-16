//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model.vion.serialization;

import commons.model.TicketDataset;
import commons.vion.MapBasedVionObject;
import commons.vion.VionObject;
import commons.vion.VionSerializer;
import commons.vion.exception.DeserializationException;
import commons.vion.exception.FieldExtractionException;
import java.util.Iterator;

public final class VionDatasetSerializer implements VionSerializer<TicketDataset> {
    private static final String NEXT_ID = "nextId";
    private static final String ALL = "all";
    private final VionTicketEntrySerializer vionTicketEntrySerializer;

    public VionDatasetSerializer(VionTicketEntrySerializer vionTicketEntrySerializer) {
        this.vionTicketEntrySerializer = vionTicketEntrySerializer;
    }

    public VionObject serialize(TicketDataset object) {
        VionObject result = new MapBasedVionObject();
        result.put("nextId", object.nextId());
        VionObject all = new MapBasedVionObject();
        object.ticketsByKey().forEach((key, value) -> {
            all.put(key, this.vionTicketEntrySerializer.serialize(value));
        });
        System.out.println(all);
        result.put("all", all);
        return result;
    }

    public TicketDataset deserialize(VionObject json) throws DeserializationException {
        if (json.keys().size() != 2) {
            throw new DeserializationException(json, "must have only fields: 'nextId', 'all'");
        } else {
            try {
                TicketDataset.Builder dataset = TicketDataset.fresh().withNextId(json.number("nextId").intValue());
                VionObject all = json.object("all");
                Iterator var4 = all.keys().iterator();

                while(var4.hasNext()) {
                    String key = (String)var4.next();
                    dataset.withEntry(key, this.vionTicketEntrySerializer.deserialize(all.object(key)));
                }

                return dataset.instance();
            } catch (FieldExtractionException var6) {
                FieldExtractionException e = var6;
                throw new DeserializationException(json, e);
            }
        }
    }
}
