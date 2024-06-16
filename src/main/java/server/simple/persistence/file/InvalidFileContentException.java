//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.file;

import java.io.IOException;

public class InvalidFileContentException extends IOException {
    public InvalidFileContentException(String message) {
        super(message);
    }

    public InvalidFileContentException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public InvalidFileContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
