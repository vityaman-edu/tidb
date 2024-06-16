//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client.service.lang.interpreter;

import client.service.lang.interpreter.exception.ExecutionException;
import java.util.List;

public interface Executable {
    void execute(List<Object> var1) throws ExecutionException;
}
