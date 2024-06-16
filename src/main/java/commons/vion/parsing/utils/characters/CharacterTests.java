//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.parsing.utils.characters;

public final class CharacterTests {
    public static CharacterTest notEqualTo(char other) {
        return new CharacterTest((ch) -> {
            return ch != other;
        }, String.format("not equal to '%s'", other));
    }

    private CharacterTests() {
        throw new AssertionError("Utility class");
    }

    public static CharacterTest equalTo(char other) {
        return new CharacterTest((ch) -> {
            return ch == other;
        }, String.format("equal to '%s'", other));
    }

    public static CharacterTest between(char min, char max) {
        return new CharacterTest((ch) -> {
            return min <= ch && ch <= max;
        }, String.format("character in ['%s','%s']", min, max));
    }

    public static CharacterTest in(Characters characters) {
        return new CharacterTest(characters::contains, String.format("in %s", characters));
    }

    public static CharacterTest in(char... chars) {
        return new CharacterTest((ch) -> {
            char[] var2 = chars;
            int var3 = chars.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                char other = var2[var4];
                if (other == ch) {
                    return true;
                }
            }

            return false;
        });
    }

    public static CharacterTest in(String chars) {
        return in(chars.toCharArray());
    }

    public static CharacterTest any(CharacterTest... expectations) {
        return new CharacterTest((ch) -> {
            CharacterTest[] var2 = expectations;
            int var3 = expectations.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                CharacterTest expectation = var2[var4];
                if (expectation.isValid(ch)) {
                    return true;
                }
            }

            return false;
        });
    }
}
