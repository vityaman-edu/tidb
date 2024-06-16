//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.controller.ui.out;

public interface Out {
    void print(String var1);

    default void println(Object object) {
        this.print(object.toString() + '\n');
    }

    default void error(String message) {
        this.println(ConsoleColor.RED.wrapped(message));
    }

    default void error(Exception exception) {
        this.error(exception.getMessage());
    }

    static Out devnull() {
        return (text) -> {
        };
    }
}
