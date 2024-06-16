//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui;

import client.controller.ui.input.EndOfInputException;
import client.controller.ui.input.Input;
import client.controller.ui.out.Out;
import client.service.CommandInterpreter;
import client.service.lang.Program;
import client.service.lang.interpreter.Instruction;
import client.service.lang.interpreter.exception.InterpreterException;
import java.text.ParseException;

public final class InteractiveConsole implements Runnable {
    private final Out out;
    private final Input in;
    private final CommandInterpreter interpreter;

    public InteractiveConsole(Input in, Out out, CommandInterpreter interpreter) {
        this.out = out;
        this.in = in;
        this.interpreter = interpreter;
    }

    public void run() {
        this.out.println("Welcome! Print 'help' to list all command!");

        while(true) {
            while(true) {
                try {
                    this.out.print("> ");
                    String input = this.in.readLine() + ' ';
                    Instruction instruction = Program.parseInstruction(input);
                    this.interpreter.execute(instruction);
                } catch (InterpreterException | ParseException var3) {
                    Exception e = var3;
                    this.out.error(e);
                } catch (EndOfInputException var4) {
                    EndOfInputException e = var4;
                    this.out.println(String.format("Pokasiki as %s", e.getMessage()));
                    return;
                }
            }
        }
    }
}
