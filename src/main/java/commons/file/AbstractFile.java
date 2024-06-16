//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.file;

import java.nio.file.Path;

public abstract class AbstractFile<T> implements File<T> {
    private final Path path;

    public AbstractFile(Path path) {
        this.path = path;
    }

    public final Path path() {
        return this.path;
    }
}
