//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.model.TicketEntry;
import commons.validation.ValidationResult;

public final class RemoveLowerMethod {
    private RemoveLowerMethod() {
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
            return "RemoveLowerMethod.Result()";
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
        private final TicketEntry sample;

        public ValidationResult validate() {
            ValidationResult.Builder var10000 = ValidationResult.of("RemoveEntryByKeyMethod", this).checking((v) -> {
                return v.sample != null;
            }, "key is null");
            TicketEntry var10001 = this.sample;
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
            return "RemoveLowerMethod.Invocation(sample=" + this.sample() + ")";
        }

        public TicketEntry sample() {
            return this.sample;
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
                    Object this$sample = this.sample();
                    Object other$sample = other.sample();
                    if (this$sample == null) {
                        if (other$sample != null) {
                            return false;
                        }
                    } else if (!this$sample.equals(other$sample)) {
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
            Object $sample = this.sample();
            result = result * 59 + ($sample == null ? 43 : $sample.hashCode());
            return result;
        }

        public Invocation(TicketEntry sample) {
            this.sample = sample;
        }
    }
}
