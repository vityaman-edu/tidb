//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.repository.exception;

public class EntryAlreadyExistsException extends CollectionException {
    public EntryAlreadyExistsException(String message) {
        super(message);
    }

    public EntryAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public EntryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public static EntryAlreadyExistsException withKey(String key) {
        return new EntryAlreadyExistsException(String.format("Entry with key '%s' already exists", key));
    }
}
