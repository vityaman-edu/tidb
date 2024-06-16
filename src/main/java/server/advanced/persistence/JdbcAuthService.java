//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.persistence;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.SneakyThrows;
import server.advanced.persistence.exception.IncorrectPasswordException;
import server.advanced.persistence.exception.UserAlreadyExistsException;
import server.advanced.persistence.exception.UserNotFoundException;
import server.simple.persistence.TicketRepositoryFactory;
import server.simple.persistence.repository.basement.TicketRepository;

public final class JdbcAuthService implements AuthService {
    private static final String SQL_INSERT_USER = "INSERT INTO users (username, password_hash, password_salt) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE username = ?";
    private final Connection connection;
    private final TicketRepositoryFactory factory;

    public JdbcAuthService(Connection connection) {
        this.connection = connection;
        this.factory = new TicketRepositoryFactory(connection);
    }

    public void register(String login, String password) throws UserAlreadyExistsException {
        try {
            String salt = this.salt();
            String hashedPassword = this.hash(password + salt);
            PreparedStatement st = this.connection
                    .prepareStatement("INSERT INTO users (username, password_hash, password_salt) VALUES (?, ?, ?)");
            st.setString(1, login);
            st.setString(2, hashedPassword);
            st.setString(3, salt);
            st.executeUpdate();
        } catch (SQLException var6) {
            SQLException e = var6;
            e.printStackTrace();
            throw new UserAlreadyExistsException(e);
        }
    }

    public TicketRepository ticketRepository(String login, String password)
            throws UserNotFoundException, IncorrectPasswordException {
        User user = this.findUserByLogin(login);
        if (!this.hash(password + user.passwordSalt()).equals(user.passportHash())) {
            throw new IncorrectPasswordException("Access denied");
        } else {
            System.out.printf("TicketRepo for user %s", user);
            return this.factory.repositoryForUser(user.login());
        }
    }

    private User findUserByLogin(String login) throws UserNotFoundException {
        try {
            PreparedStatement st = this.connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            st.setString(1, login);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return new User(result.getString(1), result.getString(2), result.getString(3));
            } else {
                throw new UserNotFoundException("no user with login " + login);
            }
        } catch (SQLException var4) {
            SQLException e = var4;
            throw new IllegalStateException(e);
        }
    }

    @SneakyThrows
    private String hash(String string) {
        MessageDigest md = MessageDigest.getInstance("MD2");
        byte[] messageDigest = md.digest(string.getBytes());
        BigInteger hash = new BigInteger(1, messageDigest);
        String hashtext = hash.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext.substring(0, 32);
    }

    private String salt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[5];
        random.nextBytes(salt);
        return new String(salt, StandardCharsets.US_ASCII);
    }
}
