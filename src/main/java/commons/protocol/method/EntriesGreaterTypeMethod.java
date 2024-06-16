//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.model.TicketEntry;
import commons.model.TicketType;
import commons.protocol.low.TransportableList;
import commons.validation.ValidationResult;

public final class EntriesGreaterTypeMethod {
    private EntriesGreaterTypeMethod() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final class Result extends MethodResult {
        private final TransportableList<TicketEntry> entries;

        public Result(Status status) {
            super(status);
            this.entries = new TransportableList();
        }

        public Result(TransportableList<TicketEntry> entries) {
            super(Status.OK);
            this.entries = entries;
        }

        public ValidationResult validate() {
            ValidationResult.Builder var10000 = ValidationResult.of("result", this).gathering(() -> {
                return super.validate();
            }).checking((v) -> {
                return this.entries != null;
            }, "entries is null");
            TransportableList var10001 = this.entries;
            var10001.getClass();
            return var10000.gathering(var10001::validate).verdict();
        }

        public String toString() {
            return "EntriesGreaterTypeMethod.Result(entries=" + this.entries() + ")";
        }

        public TransportableList<TicketEntry> entries() {
            return this.entries;
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
                    Object this$entries = this.entries();
                    Object other$entries = other.entries();
                    if (this$entries == null) {
                        if (other$entries != null) {
                            return false;
                        }
                    } else if (!this$entries.equals(other$entries)) {
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
            Object $entries = this.entries();
            result = result * 59 + ($entries == null ? 43 : $entries.hashCode());
            return result;
        }
    }

    public static final class Invocation extends MethodInvocation<Result> {
        private final TicketType type;

        public ValidationResult validate() {
            return ValidationResult.of("EntriesGreaterTypeMethod", this).checking((v) -> {
                return v.type != null;
            }, "type is null").verdict();
        }

        public Result emptyResultWithStatus(Status status) {
            return new Result(status);
        }

        public Class<Result> resultType() {
            return Result.class;
        }

        public String toString() {
            return "EntriesGreaterTypeMethod.Invocation(type=" + this.type() + ")";
        }

        public TicketType type() {
            return this.type;
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
                } else if (!super.equals(o)) {
                    return false;
                } else {
                    Object this$type = this.type();
                    Object other$type = other.type();
                    if (this$type == null) {
                        if (other$type != null) {
                            return false;
                        }
                    } else if (!this$type.equals(other$type)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Invocation;
        }

        public int hashCode() {
                int result = super.hashCode();
            Object $type = this.type();
            result = result * 59 + ($type == null ? 43 : $type.hashCode());
            return result;
        }

        public Invocation(TicketType type) {
            this.type = type;
        }
    }
}
