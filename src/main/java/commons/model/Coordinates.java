//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.model;

import commons.protocol.low.TransportableObject;
import commons.validation.ValidationResult;

public final class Coordinates implements TransportableObject {
    private final double x;
    private final double y;

    public ValidationResult validate() {
        return ValidationResult.of("coordinates", this).gathering(() -> {
            return validateX(this.x);
        }).gathering(() -> {
            return validateY(this.y);
        }).verdict();
    }

    public static ValidationResult validateX(double x) {
        return ValidationResult.of("x", x).verdict();
    }

    public static ValidationResult validateY(double y) {
        return ValidationResult.of("y", y).checking((v) -> {
            return v < 762.0;
        }, "y >= 762").verdict();
    }

    Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static CoordinatesBuilder builder() {
        return new CoordinatesBuilder();
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Coordinates)) {
            return false;
        } else {
            Coordinates other = (Coordinates)o;
            if (Double.compare(this.x(), other.x()) != 0) {
                return false;
            } else {
                return Double.compare(this.y(), other.y()) == 0;
            }
        }
    }

    public int hashCode() {
        int result = 1;
        long $x = Double.doubleToLongBits(this.x());
        result = result * 59 + (int)($x >>> 32 ^ $x);
        long $y = Double.doubleToLongBits(this.y());
        result = result * 59 + (int)($y >>> 32 ^ $y);
        return result;
    }

    public String toString() {
        return "Coordinates(x=" + this.x() + ", y=" + this.y() + ")";
    }

    public static class CoordinatesBuilder {
        private double x;
        private double y;

        CoordinatesBuilder() {
        }

        public CoordinatesBuilder x(double x) {
            this.x = x;
            return this;
        }

        public CoordinatesBuilder y(double y) {
            this.y = y;
            return this;
        }

        public Coordinates build() {
            return new Coordinates(this.x, this.y);
        }

        public String toString() {
            return "Coordinates.CoordinatesBuilder(x=" + this.x + ", y=" + this.y + ")";
        }
    }
}
