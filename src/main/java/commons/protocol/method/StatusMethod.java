//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.validation.ValidationResult;

public final class StatusMethod {
    private StatusMethod() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final class Result extends MethodResult {
        private final String message;

        public Result(Status status) {
            this(status, status.toString());
        }

        public Result(Status status, String message) {
            super(status);
            this.message = message;
        }

        public ValidationResult validate() {
            return ValidationResult.of("result", this).checking((v) -> {
                return this.message != null;
            }, "message is non null").verdict();
        }

        public String toString() {
            return "StatusMethod.Result(message=" + this.message() + ")";
        }

        public String message() {
            return this.message;
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
                    Object this$message = this.message();
                    Object other$message = other.message();
                    if (this$message == null) {
                        if (other$message != null) {
                            return false;
                        }
                    } else if (!this$message.equals(other$message)) {
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
            Object $message = this.message();
            result = result * 59 + ($message == null ? 43 : $message.hashCode());
            return result;
        }
    }

    public static final class Invocation extends MethodInvocation<Result> {
        public Invocation() {
        }

        public ValidationResult validate() {
            return ValidationResult.of("argument", this).verdict();
        }

        public Result emptyResultWithStatus(Status status) {
            return new Result(status);
        }

        public Class<Result> resultType() {
            return Result.class;
        }

        public String toString() {
            return "StatusMethod.Invocation()";
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
    }
}
