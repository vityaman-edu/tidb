//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package client;

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
    private static final String EXAMPLE = "configuration:\n  connection:\n    server:\n      host: localhost\n      port: 2222\n";
    private final Connection connection;

    private Configuration(@NonNull YamlMapping yaml) throws UnknownHostException, YamlReadingException {
        if (yaml == null) {
            throw new NullPointerException("yaml is marked non-null but is null");
        } else {
            yaml = yaml.yamlMapping("configuration");
            this.connection = new Connection(yaml.yamlMapping("connection"));
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
                throw new ReadingException(String.format("Invalid yaml file structure. Example:\n---\n%s\n---", "configuration:\n  connection:\n    server:\n      host: localhost\n      port: 2222\n"), e);
            }
        }
    }

    public String toString() {
        return "Configuration(connection=" + this.connection() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Configuration)) {
            return false;
        } else {
            Configuration other = (Configuration)o;
            Object this$connection = this.connection();
            Object other$connection = other.connection();
            if (this$connection == null) {
                if (other$connection != null) {
                    return false;
                }
            } else if (!this$connection.equals(other$connection)) {
                return false;
            }

            return true;
        }
    }

    public int hashCode() {
        int result = 1;
        Object $connection = this.connection();
        result = result * 59 + ($connection == null ? 43 : $connection.hashCode());
        return result;
    }

    public Connection connection() {
        return this.connection;
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
        private final Server server;

        private Connection(@NonNull YamlMapping yaml) throws UnknownHostException, YamlReadingException {
            if (yaml == null) {
                throw new NullPointerException("yaml is marked non-null but is null");
            } else {
                this.server = new Server(yaml.yamlMapping("server"));
            }
        }

        public String toString() {
            return "Configuration.Connection(server=" + this.server() + ")";
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Connection)) {
                return false;
            } else {
                Connection other = (Connection)o;
                Object this$server = this.server();
                Object other$server = other.server();
                if (this$server == null) {
                    if (other$server != null) {
                        return false;
                    }
                } else if (!this$server.equals(other$server)) {
                    return false;
                }

                return true;
            }
        }

        public int hashCode() {
                int result = 1;
            Object $server = this.server();
            result = result * 59 + ($server == null ? 43 : $server.hashCode());
            return result;
        }

        public Server server() {
            return this.server;
        }

        public static final class Server {
            private final InetSocketAddress address;

            private Server(@NonNull YamlMapping yaml) throws UnknownHostException, YamlReadingException {
                if (yaml == null) {
                    throw new NullPointerException("yaml is marked non-null but is null");
                } else {
                    this.address = new InetSocketAddress(InetAddress.getByName(yaml.string("host")), yaml.integer("port"));
                }
            }

            public String toString() {
                return "Configuration.Connection.Server(address=" + this.address() + ")";
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                } else if (!(o instanceof Server)) {
                    return false;
                } else {
                    Server other = (Server)o;
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
}
