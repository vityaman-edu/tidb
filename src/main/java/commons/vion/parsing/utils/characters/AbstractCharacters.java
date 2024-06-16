//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.parsing.utils.characters;

public abstract class AbstractCharacters implements Characters {
    public AbstractCharacters() {
    }

    public boolean contains(char character) {
        CharacterIterator iterator = this.iterator();

        do {
            if (!iterator.hasNext()) {
                return false;
            }
        } while(character != iterator.next());

        return true;
    }

    public char[] elements() {
        char[] result = new char[this.size()];
        int i = 0;

        for(CharacterIterator iterator = this.iterator(); iterator.hasNext(); result[i++] = iterator.next()) {
        }

        return result;
    }
}
