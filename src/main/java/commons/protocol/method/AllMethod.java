//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commons.protocol.method;

import commons.model.Entry;
import commons.protocol.low.TransportableList;
import commons.validation.ValidationResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public final class AllMethod {
    private AllMethod() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final class Result extends MethodResult {
        private static final int PART_SIZE = 5;
        private final TransportableList<Entry> entries;
        private final int remaining;

        public Result(Status status) {
            super(status);
            this.entries = new TransportableList();
            this.remaining = 0;
        }

        public Result(TransportableList<Entry> entries, int remaining) {
            super(Status.OK);
            this.entries = entries;
            this.remaining = remaining;
        }

        public Result(Status status, TransportableList<Entry> entries, int remaining) {
            super(status);
            this.entries = entries;
            this.remaining = remaining;
        }

        public ValidationResult validate() {
            ValidationResult.Builder var10000 = ValidationResult.of("result", this).checking((v) -> {
                return this.entries != null;
            }, "entries is non null").checking((v) -> {
                return this.remaining >= 0;
            }, "remaining is negative").gathering(() -> {
                return super.validate();
            });
            TransportableList var10001 = this.entries;
            var10001.getClass();
            return var10000.gathering(var10001::validate).verdict();
        }

        public Iterable<MethodResult> split() {
            LinkedList<MethodResult> splitResult = new LinkedList();
            ArrayList<Entry> partition = new ArrayList(5);
            Iterator var3 = this.entries.iterator();

            while(var3.hasNext()) {
                Entry entry = (Entry)var3.next();
                partition.add(entry);
                if (partition.size() % 5 == 0) {
                    splitResult.add(new Result(this.status(), new TransportableList(partition), this.entries.size() - (splitResult.size() + 1) * 5));
                    partition.clear();
                }
            }

            if (!partition.isEmpty()) {
                splitResult.add(new Result(this.status(), new TransportableList(partition), 0));
            }

            if (splitResult.isEmpty()) {
                splitResult.add(new Result(this.status(), new TransportableList(), 0));
            }

            return splitResult;
        }

        public String toString() {
            return "AllMethod.Result(entries=" + this.entries() + ", remaining=" + this.remaining() + ")";
        }

        public TransportableList<Entry> entries() {
            return this.entries;
        }

        public int remaining() {
            return this.remaining;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Result)) {
                return false;
            } else {
                Result other = (Result)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (!super.equals(o)) {
                    return false;
                } else if (this.remaining() != other.remaining()) {
                    return false;
                } else {
                    Object this$entries = this.entries();
                    Object other$entries = other.entries();
                    if (this$entries == null) {
                        if (other$entries == null) {
                            return true;
                        }
                    } else if (this$entries.equals(other$entries)) {
                        return true;
                    }

                    return false;
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Result;
        }

        public int hashCode() {
                int result = super.hashCode();
            result = result * 59 + this.remaining();
            Object $entries = this.entries();
            result = result * 59 + ($entries == null ? 43 : $entries.hashCode());
            return result;
        }
    }

    public static final class Invocation extends MethodInvocation<Result> {
        public Invocation() {
        }

        public ValidationResult validate() {
            return ValidationResult.of("AllMethod", this).verdict();
        }

        public Result emptyResultWithStatus(Status status) {
            return new Result(status);
        }

        public Class<Result> resultType() {
            return Result.class;
        }

        public String toString() {
            return "AllMethod.Invocation()";
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Invocation)) {
                return false;
            } else {
                Invocation other = (Invocation)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    return super.equals(o);
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Invocation;
        }

        public int hashCode() {
            int result = super.hashCode();
            return result;
        }
    }
}
