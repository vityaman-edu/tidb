//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.Iterator;

public final class FileLines implements AutoCloseable, Iterable<String> {
    private final Path path;
    private final BufferedReader file;

    public FileLines(Path path) throws IOException {
        this.path = path;

        try {
            this.file = new BufferedReader(new InputStreamReader(new FileInputStream(path.toFile())));
        } catch (IOException var3) {
            IOException e = var3;
            throw new IOException(String.format("Can't read file lines as %s", e.getMessage()), e);
        }
    }

    public void close() throws IOException {
        try {
            this.file.close();
        } catch (IOException var2) {
            IOException e = var2;
            throw new IOException(String.format("File system exception: %s", e.getMessage()), e);
        }
    }

    public Iterator<String> iterator() {
        return new FileLinesIterator();
    }

    private final class FileLinesIterator implements Iterator<String> {
        private String line;

        private FileLinesIterator() {
        }

        public boolean hasNext() {
            try {
                this.line = FileLines.this.file.readLine();
                return this.line != null;
            } catch (IOException var2) {
                IOException e = var2;
                throw new UncheckedIOException(String.format("Error while working with file %s: %s", FileLines.this.path, e.getMessage()), e);
            }
        }

        public String next() {
            return this.line;
        }
    }
}
