//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.validation.ValidationResult;

public final class RemoveLowerKeyMethod {
    private RemoveLowerKeyMethod() {
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
            return "RemoveLowerKeyMethod.Result()";
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
        private final String key;

        public ValidationResult validate() {
            return ValidationResult.of("RemoveLowerKeyMethod", this).checking((v) -> {
                return v.key != null;
            }, "key is null").verdict();
        }

        public Result emptyResultWithStatus(Status status) {
            return new Result(status);
        }

        public Class<Result> resultType() {
            return Result.class;
        }

        public String toString() {
            return "RemoveLowerKeyMethod.Invocation(key=" + this.key() + ")";
        }

        public String key() {
            return this.key;
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
            return result;
        }

        public Invocation(String key) {
            this.key = key;
        }
    }
}
