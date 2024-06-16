//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui.input;

import java.io.InputStream;
import java.util.Scanner;

public final class StreamInput implements Input {
    private final Scanner in;

    public StreamInput(InputStream in) {
        this.in = new Scanner(in);
    }

    public String readLine() {
        if (this.in.hasNextLine()) {
            return this.in.nextLine();
        } else {
            throw new EndOfInputException("End of input");
        }
    }
}
