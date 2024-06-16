//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.parsing.utils.characters;

import java.util.Arrays;

public final class ArrayCharacters extends AbstractCharacters {
    private final char[] chars;

    ArrayCharacters(char[] chars) {
        this.chars = Arrays.copyOf(chars, chars.length);
    }

    public int size() {
        return this.chars.length;
    }

    public CharacterIterator iterator() {
        return new CharacterIterator() {
            private int i = 0;

            public boolean hasNext() {
                return this.i < ArrayCharacters.this.size();
            }

            public char next() {
                return ArrayCharacters.this.chars[this.i++];
            }
        };
    }

    public String toString() {
        return Arrays.toString(this.chars).replace("\t", "\\t").replace("\n", "\\n").replace(" ", "' '");
    }
}
