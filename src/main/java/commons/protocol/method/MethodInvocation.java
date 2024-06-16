//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.protocol.low.TransportableObject;

public abstract class MethodInvocation<T extends MethodResult> implements TransportableObject {
    MethodInvocation() {
    }

    public abstract T emptyResultWithStatus(Status var1);

    public abstract Class<T> resultType();
}
