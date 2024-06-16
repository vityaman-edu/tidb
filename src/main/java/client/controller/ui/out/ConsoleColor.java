//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui.out;

public enum ConsoleColor {
    BLACK("\u001b[30m"),
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001b[34m"),
    PURPLE("\u001b[35m"),
    CYAN("\u001b[36m"),
    WHITE("\u001b[37m");

    private static final String RESET = "\u001b[0m";
    private final String color;

    private ConsoleColor(String color) {
        this.color = color;
    }

    public String wrapped(String text) {
        return this.color + text + "\u001b[0m";
    }

    public static ConsoleColor random() {
        int randint = (int)(Math.random() * 5.0 + 1.0);
        return values()[randint];
    }
}
