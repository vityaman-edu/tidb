//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model;

import commons.protocol.low.TransportableObject;
import commons.validation.ValidationResult;
import java.util.Objects;

public final class Location implements TransportableObject {
    private final float x;
    private final double y;
    private final float z;
    private final String name;

    public ValidationResult validate() {
        return ValidationResult.of("location", this).gathering(() -> {
            return validateX(this.x);
        }).gathering(() -> {
            return validateY(this.y);
        }).gathering(() -> {
            return validateZ(this.z);
        }).gathering(() -> {
            return validateName(this.name);
        }).verdict();
    }

    public static ValidationResult validateX(float x) {
        return ValidationResult.of("x", x).verdict();
    }

    public static ValidationResult validateY(double y) {
        return ValidationResult.of("y", y).verdict();
    }

    public static ValidationResult validateZ(float z) {
        return ValidationResult.of("z", z).verdict();
    }

    public static ValidationResult validateName(String name) {
        return ValidationResult.of("name", name).checking(Objects::nonNull, "name is null").verdict();
    }

    Location(float x, double y, float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public static LocationBuilder builder() {
        return new LocationBuilder();
    }

    public float x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public float z() {
        return this.z;
    }

    public String name() {
        return this.name;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Location)) {
            return false;
        } else {
            Location other = (Location)o;
            if (Float.compare(this.x(), other.x()) != 0) {
                return false;
            } else if (Double.compare(this.y(), other.y()) != 0) {
                return false;
            } else if (Float.compare(this.z(), other.z()) != 0) {
                return false;
            } else {
                Object this$name = this.name();
                Object other$name = other.name();
                if (this$name == null) {
                    if (other$name == null) {
                        return true;
                    }
                } else if (this$name.equals(other$name)) {
                    return true;
                }

                return false;
            }
        }
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.x());
        long $y = Double.doubleToLongBits(this.y());
        result = result * 59 + (int)($y >>> 32 ^ $y);
        result = result * 59 + Float.floatToIntBits(this.z());
        Object $name = this.name();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "Location(x=" + this.x() + ", y=" + this.y() + ", z=" + this.z() + ", name=" + this.name() + ")";
    }

    public static class LocationBuilder {
        private float x;
        private double y;
        private float z;
        private String name;

        LocationBuilder() {
        }

        public LocationBuilder x(float x) {
            this.x = x;
            return this;
        }

        public LocationBuilder y(double y) {
            this.y = y;
            return this;
        }

        public LocationBuilder z(float z) {
            this.z = z;
            return this;
        }

        public LocationBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Location build() {
            return new Location(this.x, this.y, this.z, this.name);
        }

        public String toString() {
            return "Location.LocationBuilder(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", name=" + this.name + ")";
        }
    }
}
