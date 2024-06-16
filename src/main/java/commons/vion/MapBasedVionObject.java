//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion;

import commons.vion.exception.ConversionException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class MapBasedVionObject extends AbstractVionObject {
    private final Map<String, Object> map = new HashMap();

    public MapBasedVionObject() {
    }

    public void putObject(String key, Object value) {
        this.map.put(key, value);
    }

    public void remove(String key) {
        this.map.remove(key);
    }

    public Object nullableObject(String key) {
        return this.map.get(key);
    }

    public Set<String> keys() {
        return this.map.keySet();
    }

    public String toString() {
        return this.map.toString();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap();
        Iterator var2 = this.map.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var2.next();
            String key = (String)entry.getKey();
            Object value = entry.getValue();
            if (value instanceof VionObject) {
                result.put(key, ((VionObject)value).toMap());
            } else {
                result.put(key, value);
            }
        }

        return result;
    }

    public static VionObject fromMap(Map<String, Object> map) throws ConversionException {
        VionObject result = new MapBasedVionObject();
        Iterator var2 = map.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var2.next();
            String key = (String)entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                result.put(key, (String)value);
            } else if (value instanceof Number) {
                result.put(key, (Number)value);
            } else if (value instanceof Enum) {
                result.put(key, (Enum)value);
            } else if (!(value instanceof List) && value instanceof Map) {
                try {
                    result.put(key, fromMap((Map)value));
                } catch (ClassCastException var7) {
                    ClassCastException e = var7;
                    throw new ConversionException(e);
                }
            }
        }

        return result;
    }
}
