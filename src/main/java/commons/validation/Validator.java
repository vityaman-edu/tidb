//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.validation;

@FunctionalInterface
public interface Validator<T> {
    ValidationResult validate(T var1);

    static <T> Validator<T> ok() {
        return new Validator<T>() {
            public ValidationResult validate(T object) {
                return ValidationResult.of("object", this).verdict();
            }
        };
    }
}
