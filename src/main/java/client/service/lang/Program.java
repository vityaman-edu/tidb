//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang;

import client.service.lang.interpreter.Instruction;
import commons.vion.parsing.utils.CharacterSequence;
import commons.vion.parsing.utils.StringSequence;
import java.text.ParseException;
import java.util.Iterator;

public final class Program implements Iterable<Instruction> {
    private final ProgramParser programParser;

    public Program(CharacterSequence source) throws ParseException {
        this.programParser = new ProgramParser(source);
    }

    public Iterator<Instruction> iterator() {
        return this.programParser.parseProgram().iterator();
    }

    public static Instruction parseInstruction(String string) throws ParseException {
        ProgramParser parser = new ProgramParser(new StringSequence(string));
        Instruction result = parser.parseInstruction();
        parser.expectEnd();
        return result;
    }
}
