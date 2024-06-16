//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.validation;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class ValidationResult {
    private final String fieldName;

    ValidationResult(String fieldName) {
        this.fieldName = fieldName;
    }

    public abstract boolean succeed();

    public boolean failed() {
        return !this.succeed();
    }

    public void throwExceptionIfFailed() throws InvalidObjectException {
        if (this.failed()) {
            throw new InvalidObjectException(this.message());
        }
    }

    public abstract String message();

    public static <T> Builder<T> of(String name, T value) {
        return new Builder(name, value);
    }

    public String toString() {
        return "ValidationResult(fieldName=" + this.fieldName() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ValidationResult)) {
            return false;
        } else {
            ValidationResult other = (ValidationResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$fieldName = this.fieldName();
                Object other$fieldName = other.fieldName();
                if (this$fieldName == null) {
                    if (other$fieldName != null) {
                        return false;
                    }
                } else if (!this$fieldName.equals(other$fieldName)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ValidationResult;
    }

    public int hashCode() {
        int result = 1;
        Object $fieldName = this.fieldName();
        result = result * 59 + ($fieldName == null ? 43 : $fieldName.hashCode());
        return result;
    }

    public String fieldName() {
        return this.fieldName;
    }

    public static final class Builder<T> {
        private final String fieldName;
        private final T fieldValue;
        private final Set<ValidationResult> invalidResults = new HashSet();

        Builder(String fieldName, T fieldValue) {
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }

        public Builder<T> checking(Predicate<T> test, String errorWhen) {
            if (this.invalidResults.isEmpty() && !test.test(this.fieldValue)) {
                this.invalidResults.add(new InvalidPrimitiveResult(this.fieldName, this.fieldValue, errorWhen));
            }

            return this;
        }

        public Builder<T> notnull(Object value, String name) {
            return this.checking((t) -> {
                return value != null;
            }, name + " is null");
        }

        public Builder<T> gathering(Supplier<ValidationResult> result) {
            if (this.invalidResults.isEmpty() && !((ValidationResult)result.get()).succeed()) {
                this.invalidResults.add(result.get());
            }

            return this;
        }

        public ValidationResult verdict() {
            return (ValidationResult)(this.invalidResults.isEmpty() ? new ValidFieldResult(this.fieldName) : new InvalidObjectResult(this.fieldName, this.invalidResults));
        }
    }
}
