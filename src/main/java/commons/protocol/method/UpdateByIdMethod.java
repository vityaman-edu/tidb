//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.model.Ticket;
import commons.validation.ValidationResult;

public final class UpdateByIdMethod {
    private UpdateByIdMethod() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final class Result extends MethodResult {
        public Result(Status status) {
            super(status);
        }

        public ValidationResult validate() {
            return ValidationResult.of("result", this).gathering(() -> {
                return super.validate();
            }).verdict();
        }

        public String toString() {
            return "UpdateByIdMethod.Result()";
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
                } else {
                    return super.equals(o);
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Result;
        }

        public int hashCode() {
            int result = super.hashCode();
            return result;
        }
    }

    public static final class Invocation extends MethodInvocation<Result> {
        private final int id;
        private final Ticket ticket;

        public ValidationResult validate() {
            ValidationResult.Builder var10000 = ValidationResult.of("InsertMethod", this).checking((v) -> {
                return v.ticket != null;
            }, "ticket is null");
            Ticket var10001 = this.ticket;
            var10001.getClass();
            return var10000.gathering(var10001::validate).verdict();
        }

        public Result emptyResultWithStatus(Status status) {
            return new Result(status);
        }

        public Class<Result> resultType() {
            return Result.class;
        }

        public String toString() {
            return "UpdateByIdMethod.Invocation(id=" + this.id() + ", ticket=" + this.ticket() + ")";
        }

        public int id() {
            return this.id;
        }

        public Ticket ticket() {
            return this.ticket;
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
                } else if (this.id() != other.id()) {
                    return false;
                } else {
                    Object this$ticket = this.ticket();
                    Object other$ticket = other.ticket();
                    if (this$ticket == null) {
                        if (other$ticket == null) {
                            return true;
                        }
                    } else if (this$ticket.equals(other$ticket)) {
                        return true;
                    }

                    return false;
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Invocation;
        }

        public int hashCode() {
                int result = super.hashCode();
            result = result * 59 + this.id();
            Object $ticket = this.ticket();
            result = result * 59 + ($ticket == null ? 43 : $ticket.hashCode());
            return result;
        }

        public Invocation(int id, Ticket ticket) {
            this.id = id;
            this.ticket = ticket;
        }
    }
}
