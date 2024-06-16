//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client;

import client.communication.EstablishedUDPConnection;
import client.communication.TicketServerConnection;
import client.controller.ui.InteractiveConsole;
import client.controller.ui.input.Input;
import client.controller.ui.input.StreamInput;
import client.controller.ui.out.Out;
import client.controller.ui.out.StreamOut;
import client.service.CommandInterpreter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException, Configuration.ReadingException {
        Configuration configuration = Configuration.load(Paths.get("res", "client-config.yml"));
        TicketServerConnection connection = new TicketServerConnection(new EstablishedUDPConnection(configuration.connection().server().address(), 30000, 40000));
        Input in = new StreamInput(System.in);
        Out out = new StreamOut(System.out);
        CommandInterpreter interpreter = new CommandInterpreter(in, out, new HashSet(), connection);
        InteractiveConsole console = new InteractiveConsole(in, out, interpreter);
        console.run();
    }
}
