//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service;

import client.communication.RemoteTicketCollection;
import client.controller.ui.input.Input;
import client.controller.ui.out.Out;
import client.service.command.All;
import client.service.command.Clear;
import client.service.command.CountGreaterThanPersonInteractive;
import client.service.command.Exec;
import client.service.command.ExecScript;
import client.service.command.Exit;
import client.service.command.FilterGreaterThanTypeInteractive;
import client.service.command.GroupByCreationDate;
import client.service.command.Help;
import client.service.command.History;
import client.service.command.InsertArgument;
import client.service.command.InsertInteractive;
import client.service.command.Login;
import client.service.command.Pwd;
import client.service.command.Register;
import client.service.command.RemoveKeyLessThan;
import client.service.command.RemoveLowerThanInteractive;
import client.service.command.UpdateByIdArgument;
import client.service.command.UpdateByIdInteractive;
import client.service.lang.interpreter.Command;
import client.service.lang.interpreter.ExecuteHistory;
import client.service.lang.interpreter.HistoryWriter;
import client.service.lang.interpreter.Instruction;
import client.service.lang.interpreter.Interpreter;
import client.service.lang.interpreter.RecursionControlInterpreter;
import client.service.lang.interpreter.SimpleInterpreter;
import client.service.lang.interpreter.exception.InterpreterException;
import commons.model.vion.serialization.VionCoordinatesSerializer;
import commons.model.vion.serialization.VionLocationSerializer;
import commons.model.vion.serialization.VionPersonSerializer;
import commons.model.vion.serialization.VionTicketSerializer;
import commons.vion.VionObject;
import java.nio.file.Path;
import java.util.Set;

public final class CommandInterpreter implements Interpreter {
    private final Interpreter interpreter;
    private final Set<Path> trace;

    public CommandInterpreter(Input in, Out out, Set<Path> trace, RemoteTicketCollection collection) {
        this.trace = trace;
        VionTicketSerializer ticketSerializer = new VionTicketSerializer(new VionCoordinatesSerializer(), new VionPersonSerializer(new VionLocationSerializer()));
        ExecuteHistory history = new ExecuteHistory(11);
        Command[] var10002 = new Command[]{Command.of(new Help(out), "help", new Class[0]), Command.of(new All(out, collection), "all", new Class[0]), Command.of(new Clear(collection), "clear", new Class[0]), null, null, null, null, null, null, null, null, null, null, null, null, null, null};
        history.getClass();
        var10002[3] = Command.of(new History(out, history::lastNInstructions), "history", new Class[0]);
        var10002[4] = Command.of(new Pwd(out), "pwd", new Class[0]);
        var10002[5] = Command.of(new Exit(), "exit", new Class[0]);
        var10002[6] = Command.of(new CountGreaterThanPersonInteractive(in, out, collection), "count_greater_than_person", new Class[0]);
        var10002[7] = Command.of(new FilterGreaterThanTypeInteractive(in, out, collection), "filter_greater_than_type", new Class[0]);
        var10002[8] = Command.of(new GroupByCreationDate(out, collection), "group_by_creation_date", new Class[0]);
        var10002[9] = Command.of(new RemoveLowerThanInteractive(in, out, collection), "remove_lower_than", new Class[0]);
        var10002[10] = Command.of(new InsertArgument(out, collection, ticketSerializer), "insert", new Class[]{String.class, VionObject.class});
        var10002[11] = Command.of(new InsertInteractive(in, out, collection), "insert", new Class[]{String.class});
        var10002[12] = Command.of(new RemoveKeyLessThan(collection), "remove_key_less_than", new Class[]{String.class});
        var10002[13] = Command.of(new UpdateByIdArgument(collection, ticketSerializer), "update", new Class[]{Integer.class, VionObject.class});
        var10002[14] = Command.of(new UpdateByIdInteractive(in, out, collection), "update", new Class[]{Integer.class});
        var10002[15] = Command.of(new Register(collection, out), "register", new Class[]{String.class, String.class});
        var10002[16] = Command.of(new Login(collection, out), "login", new Class[]{String.class, String.class});
        SimpleInterpreter simple = new SimpleInterpreter(var10002);
        Interpreter main = new RecursionControlInterpreter(new HistoryWriter(simple, history));
        simple.load(Command.of(new Exec(out, main), "exec", new Class[]{String.class}));
        simple.load(Command.of(new ExecScript(out, this), "exec_script", new Class[]{String.class}));
        this.interpreter = main;
    }

    public void execute(Instruction instruction) throws InterpreterException {
        this.interpreter.execute(instruction);
    }

    public Set<Path> trace() {
        return this.trace;
    }
}
