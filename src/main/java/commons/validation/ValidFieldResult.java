//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.validation;

final class ValidFieldResult extends ValidationResult {
    ValidFieldResult(String fieldName) {
        super(fieldName);
    }

    public boolean succeed() {
        return true;
    }

    public String message() {
        return String.format("%s is valid", this.fieldName());
    }

    public String toString() {
        return "ValidFieldResult()";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ValidFieldResult)) {
            return false;
        } else {
            ValidFieldResult other = (ValidFieldResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return super.equals(o);
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ValidFieldResult;
    }

    public int hashCode() {
        int result = super.hashCode();
        return result;
    }
}
