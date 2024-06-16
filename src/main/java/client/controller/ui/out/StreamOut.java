//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui.out;

import java.io.PrintStream;

public class StreamOut implements Out {
    private final PrintStream out;

    public StreamOut(PrintStream out) {
        this.out = out;
    }

    public void print(String text) {
        this.out.print(text);
    }
}
