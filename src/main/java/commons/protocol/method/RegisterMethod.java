//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.validation.ValidationResult;

public final class RegisterMethod {
    private RegisterMethod() {
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
            return "RegisterMethod.Result()";
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
        private final String login;
        private final String password;

        public ValidationResult validate() {
            return ValidationResult.of("RegisterMethod", this).checking((t) -> {
                return t.login != null;
            }, "login is null").checking((t) -> {
                return t.password != null;
            }, "password is null").checking((t) -> {
                return 5 <= t.login.length() && t.login.length() <= 15;
            }, "password.length not in [5, 15]").checking((t) -> {
                return 5 <= t.password.length() && t.password.length() <= 30;
            }, "password.length not in [5, 30]").verdict();
        }

        public Result emptyResultWithStatus(Status status) {
            return new Result(status);
        }

        public Class<Result> resultType() {
            return Result.class;
        }

        public String toString() {
            return "RegisterMethod.Invocation(login=" + this.login() + ", password=" + this.password() + ")";
        }

        public String login() {
            return this.login;
        }

        public String password() {
            return this.password;
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
                    Object this$login = this.login();
                    Object other$login = other.login();
                    if (this$login == null) {
                        if (other$login != null) {
                            return false;
                        }
                    } else if (!this$login.equals(other$login)) {
                        return false;
                    }

                    Object this$password = this.password();
                    Object other$password = other.password();
                    if (this$password == null) {
                        if (other$password != null) {
                            return false;
                        }
                    } else if (!this$password.equals(other$password)) {
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
            Object $login = this.login();
            result = result * 59 + ($login == null ? 43 : $login.hashCode());
            Object $password = this.password();
            result = result * 59 + ($password == null ? 43 : $password.hashCode());
            return result;
        }

        public Invocation(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }
}
