//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion;

import commons.vion.exception.FieldExtractionException;
import commons.vion.exception.FieldNotFoundException;
import commons.vion.exception.FieldTypeMismatchException;
import java.util.Set;

abstract class AbstractVionObject implements VionObject {
    AbstractVionObject() {
    }

    public abstract void remove(String var1);

    public abstract Set<String> keys();

    protected abstract Object nullableObject(String var1);

    protected abstract void putObject(String var1, Object var2);

    public void put(String key, VionObject object) {
        this.putObject(key, object);
    }

    public void put(String key, Number number) {
        this.putObject(key, number);
    }

    public void put(String key, String string) {
        this.putObject(key, string);
    }

    public void put(String key, Enum<?> constant) {
        this.putObject(key, constant);
    }

    public boolean contains(String key) {
        return this.nullableObject(key) != null;
    }

    public VionObject object(String key) throws FieldExtractionException {
        return (VionObject)this.value(VionObject.class, key);
    }

    public Number number(String key) throws FieldExtractionException {
        return (Number)this.value(Number.class, key);
    }

    public String string(String key) throws FieldExtractionException {
        return (String)this.value(String.class, key);
    }

    public <T extends Enum<T>> T constant(String key, Class<T> enumType) throws FieldExtractionException {
        try {
            return Enum.valueOf(enumType, this.string(key));
        } catch (IllegalArgumentException var4) {
            IllegalArgumentException e = var4;
            throw new FieldExtractionException(this, e);
        }
    }

    private <T> T value(Class<T> type, String key) throws FieldExtractionException {
        Object object = this.nullableObject(key);
        if (object == null) {
            throw new FieldNotFoundException(this, key);
        } else if (!type.isAssignableFrom(object.getClass())) {
            throw new FieldTypeMismatchException(this, key, type, object.getClass());
        } else {
            return type.cast(object);
        }
    }
}
