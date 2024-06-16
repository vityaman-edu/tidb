//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model.vion.serialization;

import commons.model.Person;
import commons.vion.MapBasedVionObject;
import commons.vion.VionObject;
import commons.vion.VionSerializer;
import commons.vion.exception.DeserializationException;
import commons.vion.exception.FieldExtractionException;

public final class VionPersonSerializer implements VionSerializer<Person> {
    private static final String HEIGHT = "height";
    private static final String PASSPORT_ID = "passportId";
    private static final String LOCATION = "location";
    private final VionLocationSerializer location;

    public VionPersonSerializer(VionLocationSerializer location) {
        this.location = location;
    }

    public VionObject serialize(Person object) {
        VionObject result = new MapBasedVionObject();
        result.put("height", object.height());
        result.put("passportId", object.passportId());
        result.put("location", this.location.serialize(object.location()));
        return result;
    }

    public Person deserialize(VionObject json) throws DeserializationException {
        if (json.keys().size() != 3) {
            throw new DeserializationException(json, "must have only fields: 'height', 'passportId', 'location'");
        } else {
            try {
                return Person.builder().height(json.number("height").intValue()).passportId(json.string("passportId")).location(this.location.deserialize(json.object("location"))).build();
            } catch (FieldExtractionException var3) {
                FieldExtractionException e = var3;
                throw new DeserializationException(json, e);
            }
        }
    }
}
