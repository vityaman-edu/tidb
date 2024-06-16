//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.file;

import commons.file.AbstractFile;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.Iterator;

public final class TextFile extends AbstractFile<String> {
    private static final long DEFAULT_CHAR_LIMIT = 10000L;
    private final long charLimit;

    public TextFile(Path file, long charLimit) {
        super(file);
        this.charLimit = charLimit;
    }

    public TextFile(Path file) {
        this(file, defaultCharLimit());
    }

    public void write(String content) throws IOException {
        try {
            BufferedOutputStream file = new BufferedOutputStream(new FileOutputStream(this.path().toFile()));
            Throwable var3 = null;

            try {
                file.write(content.getBytes());
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (file != null) {
                    if (var3 != null) {
                        try {
                            file.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        file.close();
                    }
                }

            }

        } catch (IOException var15) {
            IOException e = var15;
            throw new IOException(String.format("Can't write file as %s", e.getMessage()), e);
        }
    }

    public String content() throws IOException {
        StringBuilder result = new StringBuilder();

        try {
            FileLines lines = new FileLines(this.path());
            Throwable var3 = null;

            try {
                Iterator var4 = lines.iterator();

                while(var4.hasNext()) {
                    String line = (String)var4.next();
                    result.append(line).append('\n');
                    if (this.charLimit < (long)result.length()) {
                        throw new InvalidFileContentException(String.format("File size limit (%s) exceeded: found more than %s", this.charLimit, result.length()));
                    }
                }
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (lines != null) {
                    if (var3 != null) {
                        try {
                            lines.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        lines.close();
                    }
                }

            }
        } catch (UncheckedIOException var16) {
            UncheckedIOException e = var16;
            throw new IOException(e.getMessage(), e);
        }

        return result.toString();
    }

    public static long defaultCharLimit() {
        return 10000L;
    }
}
