//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Instruction {
    private final String name;
    private final List<Object> arguments;

    public Instruction(String name, List<Object> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public Instruction(String name) {
        this(name, new ArrayList());
    }

    public String name() {
        return this.name;
    }

    public List<Object> arguments() {
        return this.arguments;
    }

    public Signature signature() {
        return new Signature(this.name, (Class[])this.arguments.stream().map(Object::getClass).toArray((x$0) -> {
            return new Class[x$0];
        }));
    }

    public String toString() {
        return this.name + '(' + this.arguments.toString().substring(1) + "\b)";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Instruction that = (Instruction)o;
            return this.name.equals(that.name) && this.arguments.equals(that.arguments);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.arguments});
    }
}
