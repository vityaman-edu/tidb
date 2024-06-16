//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter;

public final class Command {
    private final Signature signature;
    private final Executable executable;

    public Command(Signature signature, Executable executable) {
        this.signature = signature;
        this.executable = executable;
    }

    public Signature signature() {
        return this.signature;
    }

    public Executable executable() {
        return this.executable;
    }

    public static Command of(Executable executable, String name, Class<?>... types) {
        return new Command(new Signature(name, types), executable);
    }
}
