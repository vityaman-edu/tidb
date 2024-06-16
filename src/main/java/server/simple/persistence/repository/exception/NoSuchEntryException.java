//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.simple.persistence.repository.exception;

public class NoSuchEntryException extends CollectionException {
    public NoSuchEntryException(String explanation) {
        super("No such ticket entry " + explanation);
    }

    public static NoSuchEntryException withId(int id) {
        return new NoSuchEntryException("with id " + id);
    }

    public static NoSuchEntryException withKey(String key) {
        return new NoSuchEntryException("with key " + key);
    }
}
