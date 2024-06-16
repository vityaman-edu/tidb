//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui.out;

public final class Colored implements Out {
    private final Out origin;
    private final ConsoleColor color;

    public Colored(ConsoleColor color, Out origin) {
        this.origin = origin;
        this.color = color;
    }

    public void print(String text) {
        this.origin.print(this.color.wrapped(text));
    }
}
