//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.command;

import client.controller.ui.out.ConsoleColor;
import client.controller.ui.out.Out;
import client.service.lang.interpreter.Executable;
import java.util.List;

public final class Help implements Executable {
    private final Out out;

    public Help(Out out) {
        this.out = out;
    }

    private void execute() {
        this.printc("help", "list available commands");
        this.printc("history", "prints last 11 executed instructions");
        this.printc("all", "prints all tickets");
        this.printc("exec \"filepath\"", "executes script");
        this.printc("insert \"key\"", "inserts entered by user ticket");
        this.printc("insert \"key\", {ticket}", "inserts provided as argument ticket");
        this.printc("update id, {ticket}", "updates ticket with provided id");
        this.printc("update id", "updates ticket entered by user");
        this.printc("clear", "deletes all elements in collection");
        this.printc("remove_key_less_than \"key\"", "removes tickets where key less than provided key");
        this.printc("group_by_creation_date", "prints tickets grouped by creation date");
        this.printc("count_greater_than_person {person}", "person provided by user");
        this.printc("remove_less_than {ticket}", "removes tickets, ticket provided by user");
        this.printc("filter_greater_than_type \"type\"", "type provided by user");
        this.printc("pwd", "prints current working directory");
        this.printc("save", "saves current collection to json file");
    }

    public void execute(List<Object> args) {
        this.execute();
    }

    private void printc(String signature, String description) {
        this.out.println(String.format("| %-45s%s", ConsoleColor.BLUE.wrapped(signature), ConsoleColor.YELLOW.wrapped(description)));
    }
}
