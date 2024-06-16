//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model.vion.serialization;

import commons.model.Ticket;
import commons.model.TicketType;
import commons.vion.MapBasedVionObject;
import commons.vion.VionObject;
import commons.vion.VionSerializer;
import commons.vion.exception.DeserializationException;
import commons.vion.exception.FieldExtractionException;

public final class VionTicketSerializer implements VionSerializer<Ticket> {
    private static final String NAME = "name";
    private static final String COORDINATES = "coordinates";
    private static final String PRICE = "price";
    private static final String TYPE = "type";
    private static final String PERSON = "person";
    private final VionCoordinatesSerializer coordinates;
    private final VionPersonSerializer person;

    public VionTicketSerializer(VionCoordinatesSerializer coordinates, VionPersonSerializer person) {
        this.coordinates = coordinates;
        this.person = person;
    }

    public VionObject serialize(Ticket object) {
        VionObject result = new MapBasedVionObject();
        result.put("name", object.name());
        result.put("coordinates", this.coordinates.serialize(object.coordinates()));
        if (object.price().isPresent()) {
            result.put("price", (Number)object.price().get());
        }

        result.put("type", object.type().toString());
        result.put("person", this.person.serialize(object.person()));
        return result;
    }

    public Ticket deserialize(VionObject json) throws DeserializationException {
        int jsonSize = json.keys().size();
        if ((jsonSize != 5 || !json.contains("price")) && jsonSize != 4) {
            throw new DeserializationException(json, "must have only fields: 'name', 'coordinates', 'person', 'type', optional 'price'");
        } else {
            try {
                Ticket.TicketBuilder result = Ticket.builder().name(json.string("name")).coordinates(this.coordinates.deserialize(json.object("coordinates"))).person(this.person.deserialize(json.object("person"))).type((TicketType)json.constant("type", TicketType.class));
                if (json.contains("price")) {
                    result.price(json.number("price").intValue());
                }

                return result.build();
            } catch (FieldExtractionException var4) {
                FieldExtractionException e = var4;
                throw new DeserializationException(json, e);
            }
        }
    }
}
