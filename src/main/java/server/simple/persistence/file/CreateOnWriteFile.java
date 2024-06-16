//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.file;

import commons.file.File;
import java.io.IOException;
import java.nio.file.Path;

public final class CreateOnWriteFile<T> implements File<T> {
    private final File<T> origin;
    private final T defaultContent;

    public CreateOnWriteFile(File<T> origin, T defaultContent) {
        this.origin = origin;
        this.defaultContent = defaultContent;
    }

    public void write(T content) throws IOException {
        java.io.File file = this.path().toFile();
        file.createNewFile();
        this.origin.write(content);
    }

    public T content() throws IOException {
        return this.path().toFile().exists() ? this.origin.content() : this.defaultContent;
    }

    public Path path() {
        return this.origin.path();
    }
}
