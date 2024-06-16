//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.validation;

final class InvalidPrimitiveResult extends ValidationResult {
    private final Object value;
    private final String explanation;

    InvalidPrimitiveResult(String fieldName, Object value, String explanation) {
        super(fieldName);
        this.value = value;
        this.explanation = explanation;
    }

    public boolean succeed() {
        return false;
    }

    public String message() {
        return String.format("%s=%s is invalid as %s", this.fieldName(), this.value.toString(), this.explanation);
    }

    public String toString() {
        return "InvalidPrimitiveResult(value=" + this.value() + ", explanation=" + this.explanation() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof InvalidPrimitiveResult)) {
            return false;
        } else {
            InvalidPrimitiveResult other = (InvalidPrimitiveResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                Object this$value = this.value();
                Object other$value = other.value();
                if (this$value == null) {
                    if (other$value != null) {
                        return false;
                    }
                } else if (!this$value.equals(other$value)) {
                    return false;
                }

                Object this$explanation = this.explanation();
                Object other$explanation = other.explanation();
                if (this$explanation == null) {
                    if (other$explanation != null) {
                        return false;
                    }
                } else if (!this$explanation.equals(other$explanation)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof InvalidPrimitiveResult;
    }

    public int hashCode() {
        int result = super.hashCode();
        Object $value = this.value();
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        Object $explanation = this.explanation();
        result = result * 59 + ($explanation == null ? 43 : $explanation.hashCode());
        return result;
    }

    public Object value() {
        return this.value;
    }

    public String explanation() {
        return this.explanation;
    }
}
