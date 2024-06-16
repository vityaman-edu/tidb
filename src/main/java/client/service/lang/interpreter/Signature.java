//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Signature {
    private final String name;
    private final Class<?>[] argumentTypes;

    public Signature(String name, Class<?>... argumentTypes) {
        this.name = name;
        this.argumentTypes = argumentTypes;
    }

    public Signature(String name) {
        this.name = name;
        this.argumentTypes = new Class<?>[0];
    }

    public String name() {
        return this.name;
    }

    public Class<?>[] argumentTypes() {
        return this.argumentTypes;
    }

    public boolean fitsTo(Signature other) {
        if (this.argumentTypes.length == other.argumentTypes.length && this.name.equals(other.name)) {
            for(int i = 0; i < this.argumentTypes.length; ++i) {
                if (!other.argumentTypes[i].isAssignableFrom(this.argumentTypes[i])) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Signature other = (Signature)o;
            return this.name.equals(other.name) && Arrays.equals(this.argumentTypes, other.argumentTypes);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, Arrays.hashCode(this.argumentTypes)});
    }

    public String toString() {
        return String.format("%s(%s\b)", this.name, ((List)Arrays.stream(this.argumentTypes).map(Class::getName).collect(Collectors.toList())).toString().substring(1));
    }
}
