//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.persistence;

public final class User {
    private final String login;
    private final String passportHash;
    private final String passwordSalt;

    public User(String login, String passportHash, String passwordSalt) {
        this.login = login;
        this.passportHash = passportHash;
        this.passwordSalt = passwordSalt;
    }

    public String login() {
        return this.login;
    }

    public String passportHash() {
        return this.passportHash;
    }

    public String passwordSalt() {
        return this.passwordSalt;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User other;
            label44: {
                other = (User)o;
                Object this$login = this.login();
                Object other$login = other.login();
                if (this$login == null) {
                    if (other$login == null) {
                        break label44;
                    }
                } else if (this$login.equals(other$login)) {
                    break label44;
                }

                return false;
            }

            Object this$passportHash = this.passportHash();
            Object other$passportHash = other.passportHash();
            if (this$passportHash == null) {
                if (other$passportHash != null) {
                    return false;
                }
            } else if (!this$passportHash.equals(other$passportHash)) {
                return false;
            }

            Object this$passwordSalt = this.passwordSalt();
            Object other$passwordSalt = other.passwordSalt();
            if (this$passwordSalt == null) {
                if (other$passwordSalt != null) {
                    return false;
                }
            } else if (!this$passwordSalt.equals(other$passwordSalt)) {
                return false;
            }

            return true;
        }
    }

    public int hashCode() {
        int result = 1;
        Object $login = this.login();
        result = result * 59 + ($login == null ? 43 : $login.hashCode());
        Object $passportHash = this.passportHash();
        result = result * 59 + ($passportHash == null ? 43 : $passportHash.hashCode());
        Object $passwordSalt = this.passwordSalt();
        result = result * 59 + ($passwordSalt == null ? 43 : $passwordSalt.hashCode());
        return result;
    }

    public String toString() {
        return "User(login=" + this.login() + ", passportHash=" + this.passportHash() + ", passwordSalt=" + this.passwordSalt() + ")";
    }
}
