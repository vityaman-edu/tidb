//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui.input;

import client.controller.ui.out.Out;
import commons.validation.ValidationResult;
import commons.validation.Validator;
import java.util.Arrays;

public final class RequestPrimitive {
    private final Input in;
    private final Out out;

    public RequestPrimitive(Input in, Out out) {
        this.in = in;
        this.out = out;
    }

    public <T> T request(Pattern<T> pattern) {
        while(true) {
            try {
                this.out.print(pattern.prefix());
                T object = pattern.convert(pattern.prettify(this.in.readLine()));
                ValidationResult validation = pattern.validate(object);
                if (validation.succeed()) {
                    return object;
                }

                this.out.error("Invalid input: " + validation.message());
            } catch (Exception var4) {
                Exception e = var4;
                this.out.error("Invalid input: " + e.getMessage());
            }
        }
    }

    public static Pattern<Double> doubleFor(String fieldName, Validator<Double> validator) {
        return new TrimmedField<Double>(fieldName, validator) {
            protected Double convert(String string) {
                return Double.parseDouble(string);
            }
        };
    }

    public static Pattern<Float> floatFor(String fieldName, Validator<Float> validator) {
        return new TrimmedField<Float>(fieldName, validator) {
            protected Float convert(String string) {
                return Float.parseFloat(string);
            }
        };
    }

    public static Pattern<String> stringFor(String fieldName, Validator<String> validator) {
        return new TrimmedField<String>(fieldName, validator) {
            protected String convert(String string) {
                return string;
            }
        };
    }

    public static Pattern<Integer> intFor(String fieldName, Validator<Integer> validator) {
        return new TrimmedField<Integer>(fieldName, validator) {
            protected Integer convert(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        };
    }

    public static <T extends Enum<T>> Pattern<T> enumFor(String fieldName, final Class<T> enumeration) {
        return new TrimmedField<T>(fieldName, Validator.ok()) {
            protected T convert(String string) {
                try {
                    return Enum.valueOf(enumeration, string.toUpperCase());
                } catch (IllegalArgumentException var3) {
                    throw new IllegalArgumentException("value must be in " + Arrays.toString(enumeration.getEnumConstants()));
                }
            }
        };
    }

    public abstract static class TrimmedField<T> extends Pattern<T> {
        private final String fieldName;

        TrimmedField(String fieldName, Validator<T> validator) {
            super(validator);
            this.fieldName = fieldName;
        }

        String prefix() {
            return this.fieldName + ": ";
        }

        String prettify(String string) {
            return string.trim();
        }
    }

    public abstract static class Pattern<T> implements Validator<T> {
        private final Validator<T> validator;

        protected Pattern(Validator<T> validator) {
            this.validator = validator;
        }

        String prefix() {
            return "";
        }

        String prettify(String string) {
            return string;
        }

        public ValidationResult validate(T object) {
            return this.validator.validate(object);
        }

        protected abstract T convert(String var1) throws Exception;
    }
}
