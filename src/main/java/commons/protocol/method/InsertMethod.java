//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.model.Ticket;
import commons.model.TicketEntry;
import commons.validation.ValidationResult;

public final class InsertMethod {
    private InsertMethod() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final class Result extends MethodResult {
        private final TicketEntry insertedEntry;

        public Result(Status status) {
            super(status);
            this.insertedEntry = null;
        }

        public Result(TicketEntry entry) {
            super(Status.OK);
            this.insertedEntry = entry;
        }

        public ValidationResult validate() {
            ValidationResult.Builder<Result> validation = ValidationResult.of("result", this).gathering(() -> {
                return super.validate();
            }).checking((v) -> {
                return v.status() != Status.OK || this.insertedEntry != null;
            }, "insertedEntry is null");
            if (this.insertedEntry != null) {
                TicketEntry var10001 = this.insertedEntry;
                var10001.getClass();
                validation = validation.gathering(var10001::validate);
            }

            return validation.verdict();
        }

        public String toString() {
            return "InsertMethod.Result(insertedEntry=" + this.insertedEntry() + ")";
        }

        public TicketEntry insertedEntry() {
            return this.insertedEntry;
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
                    Object this$insertedEntry = this.insertedEntry();
                    Object other$insertedEntry = other.insertedEntry();
                    if (this$insertedEntry == null) {
                        if (other$insertedEntry != null) {
                            return false;
                        }
                    } else if (!this$insertedEntry.equals(other$insertedEntry)) {
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
            Object $insertedEntry = this.insertedEntry();
            result = result * 59 + ($insertedEntry == null ? 43 : $insertedEntry.hashCode());
            return result;
        }
    }

    public static final class Invocation extends MethodInvocation<Result> {
        private final String key;
        private final Ticket ticket;

        public ValidationResult validate() {
            ValidationResult.Builder var10000 = ValidationResult.of("InsertMethod", this).checking((v) -> {
                return v.key != null;
            }, "key is null").checking((v) -> {
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
            return "InsertMethod.Invocation(key=" + this.key() + ", ticket=" + this.ticket() + ")";
        }

        public String key() {
            return this.key;
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
                } else {
                    Object this$key = this.key();
                    Object other$key = other.key();
                    if (this$key == null) {
                        if (other$key != null) {
                            return false;
                        }
                    } else if (!this$key.equals(other$key)) {
                        return false;
                    }

                    Object this$ticket = this.ticket();
                    Object other$ticket = other.ticket();
                    if (this$ticket == null) {
                        if (other$ticket != null) {
                            return false;
                        }
                    } else if (!this$ticket.equals(other$ticket)) {
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
            Object $key = this.key();
            result = result * 59 + ($key == null ? 43 : $key.hashCode());
            Object $ticket = this.ticket();
            result = result * 59 + ($ticket == null ? 43 : $ticket.hashCode());
            return result;
        }

        public Invocation(String key, Ticket ticket) {
            this.key = key;
            this.ticket = ticket;
        }
    }
}
