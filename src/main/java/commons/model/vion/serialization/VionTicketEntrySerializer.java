//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model.vion.serialization;

import commons.model.TicketEntry;
import commons.vion.VionObject;
import commons.vion.VionSerializer;
import commons.vion.exception.DeserializationException;
import commons.vion.exception.FieldExtractionException;
import java.text.DateFormat;
import java.text.ParseException;

public final class VionTicketEntrySerializer implements VionSerializer<TicketEntry> {
    private static final String ID = "id";
    private static final String CREATION_DATE = "creationDate";
    private final VionTicketSerializer ticket;
    private final DateFormat creationDateFormat;

    public VionTicketEntrySerializer(VionTicketSerializer ticket, DateFormat creationDateFormat) {
        this.ticket = ticket;
        this.creationDateFormat = creationDateFormat;
    }

    public VionObject serialize(TicketEntry object) {
        VionObject result = this.ticket.serialize(object);
        result.put("id", object.id());
        result.put("creationDate", this.creationDateFormat.format(object.creationDate()));
        return result;
    }

    public TicketEntry deserialize(VionObject json) throws DeserializationException {
        try {
            int id = json.number("id").intValue();
            String creationDate = json.string("creationDate");
            json.remove("id");
            json.remove("creationDate");
            TicketEntry result = new TicketEntry(id, this.creationDateFormat.parse(creationDate), this.ticket.deserialize(json));
            json.put("id", id);
            json.put("creationDate", creationDate);
            return result;
        } catch (ParseException | FieldExtractionException var5) {
            Exception e = var5;
            throw new DeserializationException(json, e);
        }
    }
}
