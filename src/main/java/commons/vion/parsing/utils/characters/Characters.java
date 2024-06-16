//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.parsing.utils.characters;

public interface Characters {
    int size();

    boolean contains(char var1);

    CharacterIterator iterator();

    char[] elements();

    static Characters of(char... characters) {
        return new ArrayCharacters(characters);
    }
}
