//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.model.Person;
import commons.validation.ValidationResult;

public final class CountGreaterPersonMethod {
    private CountGreaterPersonMethod() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final class Result extends MethodResult {
        private final int count;

        public Result(Status status) {
            super(status);
            this.count = 0;
        }

        public Result(int count) {
            super(Status.OK);
            this.count = count;
        }

        public ValidationResult validate() {
            return ValidationResult.of("result", this).checking((t) -> {
                return t.count >= 0;
            }, "count is negative").gathering(() -> {
                return super.validate();
            }).verdict();
        }

        public String toString() {
            return "CountGreaterPersonMethod.Result(count=" + this.count() + ")";
        }

        public int count() {
            return this.count;
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
                    return this.count() == other.count();
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Result;
        }

        public int hashCode() {
                int result = super.hashCode();
            result = result * 59 + this.count();
            return result;
        }
    }

    public static final class Invocation extends MethodInvocation<Result> {
        private final Person sample;

        public ValidationResult validate() {
            ValidationResult.Builder var10000 = ValidationResult.of("CountGreaterPersonMethod", this).checking((v) -> {
                return v.sample != null;
            }, "person is null");
            Person var10001 = this.sample;
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
            return "CountGreaterPersonMethod.Invocation(sample=" + this.sample() + ")";
        }

        public Person sample() {
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

        public Invocation(Person sample) {
            this.sample = sample;
        }
    }
}
