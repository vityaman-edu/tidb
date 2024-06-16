//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.parsing.utils;

import java.text.ParseException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class LineByLineSequence implements CharacterSequence {
    private final Iterator<String> lines;
    private int row;
    private int column;
    private CharacterSequence currentLine;

    public LineByLineSequence(Iterable<String> lines) {
        this.lines = lines.iterator();
        this.row = 0;
    }

    public char next() {
        if (this.hasNext()) {
            ++this.column;
            return this.currentLine.next();
        } else {
            throw new NoSuchElementException("No more character in lines");
        }
    }

    public boolean hasNext() {
        if (this.currentLine != null && this.currentLine.hasNext()) {
            return true;
        } else if (this.lines.hasNext()) {
            ++this.row;
            this.currentLine = new StringSequence((String)this.lines.next() + '\n');
            this.column = 0;
            return this.hasNext();
        } else {
            return false;
        }
    }

    public ParseException error(String message) {
        return new ParseException(String.format("Parsing error on position (%s, %s): %s", this.row, this.column, message), this.row);
    }
}
