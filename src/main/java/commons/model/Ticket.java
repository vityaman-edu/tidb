//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model;

import commons.validation.UntrustedObject;
import commons.validation.ValidationResult;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class Ticket implements UntrustedObject, Serializable {
    private final String name;
    private final Coordinates coordinates;
    private final Integer price;
    private final TicketType type;
    private final Person person;

    public final Optional<Integer> price() {
        return Optional.ofNullable(this.price);
    }

    Ticket(Ticket other) {
        this.name = other.name;
        this.coordinates = other.coordinates;
        this.price = other.price;
        this.type = other.type;
        this.person = other.person;
    }

    public Ticket(String name, Coordinates coordinates, Integer price, TicketType type, Person person) {
        this.name = name;
        this.coordinates = coordinates;
        if (price == null || price == 0) {
            price = null;
        }

        this.price = price;
        this.type = type;
        this.person = person;
    }

    public ValidationResult validate() {
        return ValidationResult.of("ticket", this).gathering(() -> {
            return validateName(this.name);
        }).gathering(() -> {
            return validateCoordinates(this.coordinates);
        }).gathering(() -> {
            return validatePrice(this.price);
        }).gathering(() -> {
            return validateType(this.type);
        }).gathering(() -> {
            return validatePerson(this.person);
        }).verdict();
    }

    public static ValidationResult validateName(String name) {
        return ValidationResult.of("name", name).checking(Objects::nonNull, "name is null").checking((v) -> {
            return !v.isEmpty();
        }, "name is empty").verdict();
    }

    public static ValidationResult validateCoordinates(Coordinates coordinates) {
        ValidationResult.Builder var10000 = ValidationResult.of("coordinates", coordinates).checking(Objects::nonNull, "coordinates is null");
        coordinates.getClass();
        return var10000.gathering(coordinates::validate).verdict();
    }

    public static ValidationResult validatePrice(Integer price) {
        return ValidationResult.of("price", price).checking((v) -> {
            return v == null || price > 0;
        }, "price must be null or positive").verdict();
    }

    public static ValidationResult validateType(TicketType type) {
        return ValidationResult.of("type", type).checking(Objects::nonNull, "type is null").verdict();
    }

    public static ValidationResult validatePerson(Person person) {
        ValidationResult.Builder var10000 = ValidationResult.of("person", person).checking(Objects::nonNull, "person is null");
        person.getClass();
        return var10000.gathering(person::validate).verdict();
    }

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }

    public String name() {
        return this.name;
    }

    public Coordinates coordinates() {
        return this.coordinates;
    }

    public TicketType type() {
        return this.type;
    }

    public Person person() {
        return this.person;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Ticket)) {
            return false;
        } else {
            Ticket other = (Ticket)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$price = this.price();
                    Object other$price = other.price();
                    if (this$price == null) {
                        if (other$price == null) {
                            break label71;
                        }
                    } else if (this$price.equals(other$price)) {
                        break label71;
                    }

                    return false;
                }

                Object this$name = this.name();
                Object other$name = other.name();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                label57: {
                    Object this$coordinates = this.coordinates();
                    Object other$coordinates = other.coordinates();
                    if (this$coordinates == null) {
                        if (other$coordinates == null) {
                            break label57;
                        }
                    } else if (this$coordinates.equals(other$coordinates)) {
                        break label57;
                    }

                    return false;
                }

                Object this$type = this.type();
                Object other$type = other.type();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                Object this$person = this.person();
                Object other$person = other.person();
                if (this$person == null) {
                    if (other$person == null) {
                        return true;
                    }
                } else if (this$person.equals(other$person)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Ticket;
    }

    public int hashCode() {
        int result = 1;
        Object $price = this.price();
        result = result * 59 + ($price == null ? 43 : $price.hashCode());
        Object $name = this.name();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $coordinates = this.coordinates();
        result = result * 59 + ($coordinates == null ? 43 : $coordinates.hashCode());
        Object $type = this.type();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $person = this.person();
        result = result * 59 + ($person == null ? 43 : $person.hashCode());
        return result;
    }

    public String toString() {
        return "Ticket(name=" + this.name() + ", coordinates=" + this.coordinates() + ", price=" + this.price() + ", type=" + this.type() + ", person=" + this.person() + ")";
    }

    public static class TicketBuilder {
        private String name;
        private Coordinates coordinates;
        private Integer price;
        private TicketType type;
        private Person person;

        TicketBuilder() {
        }

        public TicketBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TicketBuilder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public TicketBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public TicketBuilder type(TicketType type) {
            this.type = type;
            return this;
        }

        public TicketBuilder person(Person person) {
            this.person = person;
            return this;
        }

        public Ticket build() {
            return new Ticket(this.name, this.coordinates, this.price, this.type, this.person);
        }

        public String toString() {
            return "Ticket.TicketBuilder(name=" + this.name + ", coordinates=" + this.coordinates + ", price=" + this.price + ", type=" + this.type + ", person=" + this.person + ")";
        }
    }
}
