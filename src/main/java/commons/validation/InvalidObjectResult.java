//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.validation;

import java.util.Set;
import java.util.stream.Collectors;

final class InvalidObjectResult extends ValidationResult {
    private final Set<ValidationResult> reports;

    InvalidObjectResult(String fieldName, Set<ValidationResult> reports) {
        super(fieldName);
        this.reports = reports;
    }

    public boolean succeed() {
        return false;
    }

    public String message() {
        String reasons = (String)this.reports.stream().map(ValidationResult::message).collect(Collectors.joining());
        return String.format("%s is invalid because %s", this.fieldName(), reasons);
    }

    public String toString() {
        return "InvalidObjectResult(reports=" + this.reports() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof InvalidObjectResult)) {
            return false;
        } else {
            InvalidObjectResult other = (InvalidObjectResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                Object this$reports = this.reports();
                Object other$reports = other.reports();
                if (this$reports == null) {
                    if (other$reports != null) {
                        return false;
                    }
                } else if (!this$reports.equals(other$reports)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof InvalidObjectResult;
    }

    public int hashCode() {
        int result = super.hashCode();
        Object $reports = this.reports();
        result = result * 59 + ($reports == null ? 43 : $reports.hashCode());
        return result;
    }

    public Set<ValidationResult> reports() {
        return this.reports;
    }
}
