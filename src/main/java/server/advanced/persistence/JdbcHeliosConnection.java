//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.persistence;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class JdbcHeliosConnection {
    public JdbcHeliosConnection() {
    }

    public static Connection openLocal(String user, String pwd) throws IOException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://pg:5432/studs", user, pwd);
        } catch (SQLException | ClassNotFoundException var3) {
            Exception e = var3;
            throw new IOException(e);
        }
    }

    public static Connection openRemoteUsingSSH(String user, String pwd) throws IOException {
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, "se.ifmo.ru", 2222);
            session.setPassword(pwd);
            session.setConfig(config);
            session.connect();
            session.setPortForwardingL(4356, "pg", 5432);
            return DriverManager.getConnection("jdbc:postgresql://localhost:4356/studs", user, pwd);
        } catch (SQLException | JSchException var6) {
            Exception e = var6;
            throw new IOException(e);
        }
    }
}
