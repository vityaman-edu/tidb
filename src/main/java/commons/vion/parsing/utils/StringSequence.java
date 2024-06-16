//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.parsing.utils;

import java.text.ParseException;

public final class StringSequence implements CharacterSequence {
    private final String string;
    private int currentPosition;

    public StringSequence(String string) {
        this.string = string;
        this.currentPosition = 0;
    }

    public char next() {
        return this.string.charAt(this.currentPosition++);
    }

    public boolean hasNext() {
        return this.currentPosition < this.string.length();
    }

    public ParseException error(String message) {
        return new ParseException(String.format("Parsing error on position %d: %s (%s)", this.currentPosition, message, this.string.substring(Math.max(this.currentPosition - 10, 0), Math.min(this.currentPosition + 10, this.string.length()))), this.currentPosition);
    }
}
