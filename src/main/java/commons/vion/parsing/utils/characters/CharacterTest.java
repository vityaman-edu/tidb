//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.parsing.utils.characters;

public final class CharacterTest {
    private final Predicate test;
    private final String description;

    public CharacterTest(Predicate test, String description) {
        this.test = test;
        this.description = description;
    }

    public CharacterTest(Predicate test) {
        this(test, "Invalid character");
    }

    public boolean isValid(char character) {
        return this.test.isValid(character);
    }

    public String description() {
        return this.description;
    }

    @FunctionalInterface
    public interface Predicate {
        boolean isValid(char var1);
    }
}
