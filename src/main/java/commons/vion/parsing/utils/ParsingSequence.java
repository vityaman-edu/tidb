//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.vion.parsing.utils;

import commons.vion.parsing.utils.characters.CharacterTest;
import commons.vion.parsing.utils.characters.CharacterTests;
import commons.vion.parsing.utils.characters.Characters;
import java.text.ParseException;

public final class ParsingSequence {
    private static final char DEFAULT_END = '\u0000';
    private static final Characters DEFAULT_WHITESPACES = Characters.of(new char[]{' ', '\t', '\n'});
    private final char end;
    private final Characters whitespaces;
    private final CharacterSequence sequence;
    private char current;

    public ParsingSequence(CharacterSequence sequence) throws ParseException {
        this(sequence, '\u0000', DEFAULT_WHITESPACES);
    }

    public ParsingSequence(CharacterSequence sequence, char end, Characters whitespaces) throws ParseException {
        this.end = end;
        this.whitespaces = whitespaces;
        this.sequence = sequence;
        this.current = (char)(end + 1);
        this.take();
    }

    public boolean isCurrent(CharacterTest test) {
        return test.isValid(this.current);
    }

    public char take() throws ParseException {
        if (this.isEnded()) {
            throw this.error("Expected char, found end of sequence");
        } else {
            char taken = this.current;
            if (this.sequence.hasNext()) {
                this.current = this.sequence.next();
            } else {
                this.current = this.end;
            }

            return taken;
        }
    }

    public boolean take(CharacterTest test) throws ParseException {
        if (test.isValid(this.current)) {
            this.take();
            return true;
        } else {
            return false;
        }
    }

    public void expect(String expected) throws ParseException {
        char[] var2 = expected.toCharArray();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            char ch = var2[var4];
            this.expect(ch);
        }

    }

    public void expect(char expected) throws ParseException {
        this.expect(CharacterTests.equalTo(expected));
    }

    public void expect(CharacterTest expectation) throws ParseException {
        if (!this.take(expectation)) {
            throw this.error(String.format("Found '%s', but expected %s", this.current, expectation.description()));
        }
    }

    public void expectWhitespace() throws ParseException {
        this.expect(CharacterTests.in(this.whitespaces));
    }

    public void expectEnded() throws ParseException {
        if (!this.isEnded()) {
            throw this.error(String.format("Expected end of input, found '%s'", this.current));
        }
    }

    public void skipWhitespaces() throws ParseException {
        while(this.take(CharacterTests.in(this.whitespaces))) {
        }

    }

    public boolean isEnded() {
        return this.isCurrent(CharacterTests.equalTo(this.end));
    }

    public ParseException error(String message) {
        return this.sequence.error(message);
    }
}
