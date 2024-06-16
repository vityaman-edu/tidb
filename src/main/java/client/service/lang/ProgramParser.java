//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang;

import client.service.lang.interpreter.Instruction;
import commons.vion.VionParser;
import commons.vion.exception.UncheckedParseException;
import commons.vion.parsing.utils.CharacterSequence;
import commons.vion.parsing.utils.characters.CharacterTest;
import commons.vion.parsing.utils.characters.CharacterTests;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class ProgramParser extends VionParser {
    ProgramParser(CharacterSequence source) throws ParseException {
        super(source);
    }

    void expectEnd() throws ParseException {
        this.sequence.skipWhitespaces();
        this.sequence.expectEnded();
    }

    Iterable<Instruction> parseProgram() {
        return () -> {
            return new Iterator<Instruction>() {
                public boolean hasNext() {
                    return !ProgramParser.this.sequence.isEnded();
                }

                public Instruction next() {
                    try {
                        return ProgramParser.this.parseInstruction();
                    } catch (ParseException var2) {
                        ParseException e = var2;
                        throw new UncheckedParseException(e);
                    }
                }
            };
        };
    }

    Instruction parseInstruction() throws ParseException {
        this.sequence.skipWhitespaces();
        String name = this.parseChars();
        this.sequence.expectWhitespace();
        this.sequence.skipWhitespaces();
        if (!this.sequence.isEnded() && !this.sequence.isCurrent(CharacterTests.any(new CharacterTest[]{CharacterTests.between('a', 'z'), CharacterTests.between('A', 'Z'), CharacterTests.equalTo('_')}))) {
            List<Object> arguments = this.parseValues();
            this.sequence.skipWhitespaces();
            return new Instruction(name, arguments);
        } else {
            return new Instruction(name, new ArrayList());
        }
    }
}
