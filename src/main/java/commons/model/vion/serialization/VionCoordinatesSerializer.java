//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model.vion.serialization;

import commons.model.Coordinates;
import commons.vion.MapBasedVionObject;
import commons.vion.VionObject;
import commons.vion.VionSerializer;
import commons.vion.exception.DeserializationException;
import commons.vion.exception.FieldExtractionException;

public final class VionCoordinatesSerializer implements VionSerializer<Coordinates> {
    private static final String X = "x";
    private static final String Y = "y";

    public VionCoordinatesSerializer() {
    }

    public VionObject serialize(Coordinates object) {
        VionObject result = new MapBasedVionObject();
        result.put("x", object.x());
        result.put("y", object.y());
        return result;
    }

    public Coordinates deserialize(VionObject json) throws DeserializationException {
        if (json.keys().size() != 2) {
            throw new DeserializationException(json, "must have only fields: 'x', 'y'");
        } else {
            try {
                return Coordinates.builder().x(json.number("x").doubleValue()).y(json.number("y").doubleValue()).build();
            } catch (FieldExtractionException var3) {
                FieldExtractionException e = var3;
                throw new DeserializationException(json, e);
            }
        }
    }
}
