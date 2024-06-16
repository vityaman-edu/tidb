//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model;

import commons.protocol.low.TransportableObject;
import commons.validation.ValidationResult;
import java.util.Objects;

public final class Person implements Comparable<Person>, TransportableObject {
    private final int height;
    private final String passportId;
    private final Location location;

    public int compareTo(Person person) {
        return (int)Math.signum((float)(this.height - person.height));
    }

    public ValidationResult validate() {
        return ValidationResult.of("person", this).gathering(() -> {
            return validateHeight(this.height);
        }).gathering(() -> {
            return validatePassportId(this.passportId);
        }).gathering(() -> {
            return validateLocation(this.location);
        }).verdict();
    }

    public static ValidationResult validateHeight(int height) {
        return ValidationResult.of("height", height).checking((v) -> {
            return v > 0;
        }, "height is positive").verdict();
    }

    public static ValidationResult validatePassportId(String passportId) {
        return ValidationResult.of("passportId", passportId).checking(Objects::nonNull, "passportId must be not null").checking((v) -> {
            return 5 <= v.length() && v.length() <= 47;
        }, "passportId.length must be in [5, 47]").verdict();
    }

    public static ValidationResult validateLocation(Location location) {
        ValidationResult.Builder var10000 = ValidationResult.of("location", location).checking(Objects::nonNull, "location must be not null");
        location.getClass();
        return var10000.gathering(location::validate).verdict();
    }

    Person(int height, String passportId, Location location) {
        this.height = height;
        this.passportId = passportId;
        this.location = location;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public int height() {
        return this.height;
    }

    public String passportId() {
        return this.passportId;
    }

    public Location location() {
        return this.location;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Person)) {
            return false;
        } else {
            Person other = (Person)o;
            if (this.height() != other.height()) {
                return false;
            } else {
                Object this$passportId = this.passportId();
                Object other$passportId = other.passportId();
                if (this$passportId == null) {
                    if (other$passportId != null) {
                        return false;
                    }
                } else if (!this$passportId.equals(other$passportId)) {
                    return false;
                }

                Object this$location = this.location();
                Object other$location = other.location();
                if (this$location == null) {
                    if (other$location != null) {
                        return false;
                    }
                } else if (!this$location.equals(other$location)) {
                    return false;
                }

                return true;
            }
        }
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.height();
        Object $passportId = this.passportId();
        result = result * 59 + ($passportId == null ? 43 : $passportId.hashCode());
        Object $location = this.location();
        result = result * 59 + ($location == null ? 43 : $location.hashCode());
        return result;
    }

    public String toString() {
        return "Person(height=" + this.height() + ", passportId=" + this.passportId() + ", location=" + this.location() + ")";
    }

    public static class PersonBuilder {
        private int height;
        private String passportId;
        private Location location;

        PersonBuilder() {
        }

        public PersonBuilder height(int height) {
            this.height = height;
            return this;
        }

        public PersonBuilder passportId(String passportId) {
            this.passportId = passportId;
            return this;
        }

        public PersonBuilder location(Location location) {
            this.location = location;
            return this;
        }

        public Person build() {
            return new Person(this.height, this.passportId, this.location);
        }

        public String toString() {
            return "Person.PersonBuilder(height=" + this.height + ", passportId=" + this.passportId + ", location=" + this.location + ")";
        }
    }
}
