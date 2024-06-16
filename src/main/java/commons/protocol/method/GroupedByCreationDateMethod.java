//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.model.TicketEntry;
import commons.protocol.low.TransportableList;
import commons.protocol.low.TransportableMap;
import commons.validation.ValidationResult;
import java.time.LocalDate;

public final class GroupedByCreationDateMethod {
    private GroupedByCreationDateMethod() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final class Result extends MethodResult {
        private static final int PART_SIZE = 10;
        private final TransportableMap<LocalDate, TransportableList<TicketEntry>> entriesGroupedByCreationDate;

        public Result(Status status) {
            super(status);
            this.entriesGroupedByCreationDate = new TransportableMap();
        }

        public Result(TransportableMap<LocalDate, TransportableList<TicketEntry>> entriesGroupedByCreationDate) {
            super(Status.OK);
            this.entriesGroupedByCreationDate = entriesGroupedByCreationDate;
        }

        public ValidationResult validate() {
            ValidationResult.Builder var10000 = ValidationResult.of("result", this).gathering(() -> {
                return super.validate();
            }).checking((v) -> {
                return v.entriesGroupedByCreationDate != null;
            }, "entriesGroupedByCreationDate is null");
            TransportableMap var10001 = this.entriesGroupedByCreationDate;
            var10001.getClass();
            return var10000.gathering(var10001::validate).verdict();
        }

        public String toString() {
            return "GroupedByCreationDateMethod.Result(entriesGroupedByCreationDate=" + this.entriesGroupedByCreationDate() + ")";
        }

        public TransportableMap<LocalDate, TransportableList<TicketEntry>> entriesGroupedByCreationDate() {
            return this.entriesGroupedByCreationDate;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Result)) {
                return false;
            } else {
                Result other = (Result)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (!super.equals(o)) {
                    return false;
                } else {
                    Object this$entriesGroupedByCreationDate = this.entriesGroupedByCreationDate();
                    Object other$entriesGroupedByCreationDate = other.entriesGroupedByCreationDate();
                    if (this$entriesGroupedByCreationDate == null) {
                        if (other$entriesGroupedByCreationDate != null) {
                            return false;
                        }
                    } else if (!this$entriesGroupedByCreationDate.equals(other$entriesGroupedByCreationDate)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Result;
        }

        public int hashCode() {
                int result = super.hashCode();
            Object $entriesGroupedByCreationDate = this.entriesGroupedByCreationDate();
            result = result * 59 + ($entriesGroupedByCreationDate == null ? 43 : $entriesGroupedByCreationDate.hashCode());
            return result;
        }
    }

    public static final class Invocation extends MethodInvocation<Result> {
        public ValidationResult validate() {
            return ValidationResult.of("GroupedByCreationDateMethod", this).verdict();
        }

        public Result emptyResultWithStatus(Status status) {
            return new Result(status);
        }

        public Class<Result> resultType() {
            return Result.class;
        }

        public String toString() {
            return "GroupedByCreationDateMethod.Invocation()";
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Invocation)) {
                return false;
            } else {
                Invocation other = (Invocation)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    return super.equals(o);
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Invocation;
        }

        public int hashCode() {
            int result = super.hashCode();
            return result;
        }

        public Invocation() {
        }
    }
}
