//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.file;

import java.io.IOException;
import java.nio.file.Path;

public interface File<T> {
    void write(T var1) throws IOException;

    T content() throws IOException;

    Path path();
}
