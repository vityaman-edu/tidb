//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui.out;

public final class Prefixed implements Out {
    private final Out origin;
    private final String prefix;

    public Prefixed(String prefix, Out origin) {
        this.origin = origin;
        this.prefix = prefix;
    }

    public void print(String text) {
        this.origin.print(this.prefix + text);
    }
}
