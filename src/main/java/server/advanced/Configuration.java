//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.exceptions.YamlReadingException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;
import lombok.NonNull;

public final class Configuration {
    private static final String EXAMPLE = "configuration:\n  connection:\n    host: localhost\n    port: 2222\n  database:\n    username: yourname\n    password: yourpass\n  threads:\n    deserialization: 10\n    service: 10\n    serialization: 10";
    private final Connection connection;
    private final Database database;
    private final Threads threads;

    private Configuration(@NonNull YamlMapping yaml) throws UnknownHostException, YamlReadingException {
        if (yaml == null) {
            throw new NullPointerException("yaml is marked non-null but is null");
        } else {
            yaml = yaml.yamlMapping("configuration");
            this.connection = new Connection(yaml.yamlMapping("connection"));
            this.database = new Database(yaml.yamlMapping("database"));
            this.threads = new Threads(yaml.yamlMapping("threads"));
        }
    }

    public static Configuration load(@NonNull Path path) throws ReadingException {
        if (path == null) {
            throw new NullPointerException("path is marked non-null but is null");
        } else {
            try {
                return new Configuration(Yaml.createYamlInput(path.toFile()).readYamlMapping());
            } catch (IOException | YamlReadingException var2) {
                Exception e = var2;
                throw new ReadingException(e);
            } catch (NullPointerException var3) {
                NullPointerException e = var3;
                throw new ReadingException(String.format("Invalid yaml file structure. Example:\n---\n%s\n---", "configuration:\n  connection:\n    host: localhost\n    port: 2222\n  database:\n    username: yourname\n    password: yourpass\n  threads:\n    deserialization: 10\n    service: 10\n    serialization: 10"), e);
            }
        }
    }

    public String toString() {
        return "Configuration(connection=" + this.connection() + ", database=" + this.database() + ", threads=" + this.threads() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Configuration)) {
            return false;
        } else {
            Configuration other;
            label44: {
                other = (Configuration)o;
                Object this$connection = this.connection();
                Object other$connection = other.connection();
                if (this$connection == null) {
                    if (other$connection == null) {
                        break label44;
                    }
                } else if (this$connection.equals(other$connection)) {
                    break label44;
                }

                return false;
            }

            Object this$database = this.database();
            Object other$database = other.database();
            if (this$database == null) {
                if (other$database != null) {
                    return false;
                }
            } else if (!this$database.equals(other$database)) {
                return false;
            }

            Object this$threads = this.threads();
            Object other$threads = other.threads();
            if (this$threads == null) {
                if (other$threads != null) {
                    return false;
                }
            } else if (!this$threads.equals(other$threads)) {
                return false;
            }

            return true;
        }
    }

    public int hashCode() {
        int result = 1;
        Object $connection = this.connection();
        result = result * 59 + ($connection == null ? 43 : $connection.hashCode());
        Object $database = this.database();
        result = result * 59 + ($database == null ? 43 : $database.hashCode());
        Object $threads = this.threads();
        result = result * 59 + ($threads == null ? 43 : $threads.hashCode());
        return result;
    }

    public Connection connection() {
        return this.connection;
    }

    public Database database() {
        return this.database;
    }

    public Threads threads() {
        return this.threads;
    }

    public static class Threads {
        private final int deserialization;
        private final int service;
        private final int serialization;

        public Threads(YamlMapping yaml) {
            this.deserialization = yaml.integer("deserialization");
            this.service = yaml.integer("service");
            this.serialization = yaml.integer("serialization");
        }

        public String toString() {
            return "Configuration.Threads(deserialization=" + this.deserialization() + ", service=" + this.service() + ", serialization=" + this.serialization() + ")";
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Threads)) {
                return false;
            } else {
                Threads other = (Threads)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (this.deserialization() != other.deserialization()) {
                    return false;
                } else if (this.service() != other.service()) {
                    return false;
                } else {
                    return this.serialization() == other.serialization();
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof Threads;
        }

        public int hashCode() {
                int result = 1;
            result = result * 59 + this.deserialization();
            result = result * 59 + this.service();
            result = result * 59 + this.serialization();
            return result;
        }

        public int deserialization() {
            return this.deserialization;
        }

        public int service() {
            return this.service;
        }

        public int serialization() {
            return this.serialization;
        }
    }

    public static final class Database {
        private final String username;
        private final String password;

        public Database(YamlMapping yaml) {
            this.username = yaml.string("username");
            this.password = yaml.string("password");
        }

        public String toString() {
            return "Configuration.Database(username=" + this.username() + ", password=" + this.password() + ")";
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Database)) {
                return false;
            } else {
                Database other = (Database)o;
                Object this$username = this.username();
                Object other$username = other.username();
                if (this$username == null) {
                    if (other$username != null) {
                        return false;
                    }
                } else if (!this$username.equals(other$username)) {
                    return false;
                }

                Object this$password = this.password();
                Object other$password = other.password();
                if (this$password == null) {
                    if (other$password != null) {
                        return false;
                    }
                } else if (!this$password.equals(other$password)) {
                    return false;
                }

                return true;
            }
        }

        public int hashCode() {
                int result = 1;
            Object $username = this.username();
            result = result * 59 + ($username == null ? 43 : $username.hashCode());
            Object $password = this.password();
            result = result * 59 + ($password == null ? 43 : $password.hashCode());
            return result;
        }

        public String username() {
            return this.username;
        }

        public String password() {
            return this.password;
        }
    }

    public static class ReadingException extends Exception {
        public ReadingException(String message, Throwable cause) {
            super(String.format("Error while reading yaml config: %s", message), cause);
        }

        public ReadingException(Throwable cause) {
            this(cause.getMessage(), cause);
        }
    }

    public static final class Connection {
        private final InetSocketAddress address;

        private Connection(@NonNull YamlMapping yaml) throws UnknownHostException, YamlReadingException {
            if (yaml == null) {
                throw new NullPointerException("yaml is marked non-null but is null");
            } else {
                this.address = new InetSocketAddress(InetAddress.getByName(yaml.string("host")), yaml.integer("port"));
            }
        }

        public String toString() {
            return "Configuration.Connection(address=" + this.address() + ")";
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Connection)) {
                return false;
            } else {
                Connection other = (Connection)o;
                Object this$address = this.address();
                Object other$address = other.address();
                if (this$address == null) {
                    if (other$address != null) {
                        return false;
                    }
                } else if (!this$address.equals(other$address)) {
                    return false;
                }

                return true;
            }
        }

        public int hashCode() {
                int result = 1;
            Object $address = this.address();
            result = result * 59 + ($address == null ? 43 : $address.hashCode());
            return result;
        }

        public InetSocketAddress address() {
            return this.address;
        }
    }
}
