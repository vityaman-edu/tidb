//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model.vion.serialization;

import commons.model.Location;
import commons.vion.MapBasedVionObject;
import commons.vion.VionObject;
import commons.vion.VionSerializer;
import commons.vion.exception.DeserializationException;
import commons.vion.exception.FieldExtractionException;

public final class VionLocationSerializer implements VionSerializer<Location> {
    private static final String NAME = "name";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String Z = "z";

    public VionLocationSerializer() {
    }

    public VionObject serialize(Location object) {
        VionObject result = new MapBasedVionObject();
        result.put("name", object.name());
        result.put("x", object.x());
        result.put("y", object.y());
        result.put("z", object.z());
        return result;
    }

    public Location deserialize(VionObject json) throws DeserializationException {
        if (json.keys().size() != 4) {
            throw new DeserializationException(json, "must have only fields: 'name', 'x', 'y', 'z'");
        } else {
            try {
                return Location.builder().name(json.string("name")).x(json.number("x").floatValue()).y(json.number("y").doubleValue()).z(json.number("z").floatValue()).build();
            } catch (FieldExtractionException var3) {
                FieldExtractionException e = var3;
                throw new DeserializationException(json, e);
            }
        }
    }
}
