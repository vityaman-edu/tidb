//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.persistence;

import commons.model.Coordinates;
import commons.model.Entry;
import commons.model.Location;
import commons.model.Person;
import commons.model.Ticket;
import commons.model.TicketEntry;
import commons.model.TicketType;
import commons.validation.InvalidObjectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import server.simple.persistence.repository.basement.TicketRepository;
import server.simple.persistence.repository.exception.EntryAlreadyExistsException;
import server.simple.persistence.repository.exception.NoSuchEntryException;

public final class JdbcTicketRepository implements TicketRepository {
    private static final Logger log = Logger.getLogger(JdbcTicketRepository.class.getName());
    private final String ownername;
    private final Connection conn;
    private static final String SQL_SELECT_TICKETS_BY_USERNAME = "SELECT * FROM tickets WHERE ownername = ?";
    private static final String SQL_INSERT_TICKET = "INSERT INTO tickets (key, name, c_x, c_y, price, type, p_height, p_passport, p_l_x , p_l_y, p_l_z , p_l_name, ownername) VALUES (?, ?, ?, ?, ?, CAST(? AS ticket_type), ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ENTRY_BY_KEY = "SELECT * FROM tickets WHERE key = ? AND ownername = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE tickets SET name = ?, c_x = ?, c_y = ?, price = ?, type = CAST(? AS ticket_type), p_height = ?, p_passport = ?, p_l_x = ? , p_l_y = ?, p_l_z = ?, p_l_name = ? WHERE id = ? AND ownername = ?";
    private static final String SQL_DELETE_ALL = "DELETE FROM tickets WHERE ownername = ?";
    private static final String SQL_DELETE_BY_KEY = "DELETE FROM tickets WHERE key = ? AND ownername = ?";
    private static final String SQL_DELETE_LOWER_ID = "DELETE FROM tickets WHERE id < ? AND ownername = ?";
    private static final String SQL_DELETE_LOWER_KEY = "DELETE FROM tickets WHERE key < ? AND ownername = ?";
    private static final String SQL_COUNT_GREATER_PERSON = "SELECT COUNT(*) FROM tickets WHERE p_height > ? AND ownername = ?";
    private static final String SQL_SELECT_GREATER_TYPE = "SELECT * FROM tickets WHERE type > ? AND ownername = ?";

    public JdbcTicketRepository(String ownername, Connection connection) {
        this.ownername = ownername;
        this.conn = connection;
    }

    public Collection<Entry> all() {
        try {
            PreparedStatement st = this.conn.prepareStatement("SELECT * FROM tickets WHERE ownername = ?");
            st.setString(1, this.ownername);
            ResultSet result = st.executeQuery();
            System.out.println("SELECT * FROM tickets WHERE ownername = ?");
            List<Entry> entries = new LinkedList();

            while(result.next()) {
                entries.add(new Entry(result.getString("key"), this.createTicketEntry(result)));
            }

            return entries;
        } catch (SQLException var4) {
            SQLException e = var4;
            throw new IllegalStateException(e);
        }
    }

    public TicketEntry insert(String key, Ticket ticket) throws EntryAlreadyExistsException {
        try {
            PreparedStatement insert = this.conn.prepareStatement("INSERT INTO tickets (key, name, c_x, c_y, price, type, p_height, p_passport, p_l_x , p_l_y, p_l_z , p_l_name, ownername) VALUES (?, ?, ?, ?, ?, CAST(? AS ticket_type), ?, ?, ?, ?, ?, ?, ?)");
            Throwable var4 = null;

            TicketEntry var8;
            try {
                PreparedStatement retrieve = this.conn.prepareStatement("SELECT * FROM tickets WHERE key = ? AND ownername = ?");
                Throwable var6 = null;

                try {
                    insert.setString(1, key);
                    this.setTicketFieldsTotal11(insert, ticket, 2);
                    insert.setString(13, this.ownername);
                    insert.executeUpdate();
                    log.info("INSERT INTO tickets (key, name, c_x, c_y, price, type, p_height, p_passport, p_l_x , p_l_y, p_l_z , p_l_name, ownername) VALUES (?, ?, ?, ?, ?, CAST(? AS ticket_type), ?, ?, ?, ?, ?, ?, ?)");
                    retrieve.setString(1, key);
                    retrieve.setString(2, this.ownername);
                    ResultSet result = retrieve.executeQuery();
                    log.info("SELECT * FROM tickets WHERE key = ? AND ownername = ?");
                    if (!result.next()) {
                        throw new IllegalStateException();
                    }

                    var8 = this.createTicketEntry(result);
                } catch (Throwable var33) {
                    var6 = var33;
                    throw var33;
                } finally {
                    if (retrieve != null) {
                        if (var6 != null) {
                            try {
                                retrieve.close();
                            } catch (Throwable var32) {
                                var6.addSuppressed(var32);
                            }
                        } else {
                            retrieve.close();
                        }
                    }

                }
            } catch (Throwable var35) {
                var4 = var35;
                throw var35;
            } finally {
                if (insert != null) {
                    if (var4 != null) {
                        try {
                            insert.close();
                        } catch (Throwable var31) {
                            var4.addSuppressed(var31);
                        }
                    } else {
                        insert.close();
                    }
                }

            }

            return var8;
        } catch (SQLException var37) {
            SQLException e = var37;
            e.printStackTrace();
            throw new EntryAlreadyExistsException(e);
        }
    }

    public void updateById(int id, Ticket ticket) throws NoSuchEntryException {
        try {
            PreparedStatement st = this.conn.prepareStatement("UPDATE tickets SET name = ?, c_x = ?, c_y = ?, price = ?, type = CAST(? AS ticket_type), p_height = ?, p_passport = ?, p_l_x = ? , p_l_y = ?, p_l_z = ?, p_l_name = ? WHERE id = ? AND ownername = ?");
            Throwable var4 = null;

            try {
                this.setTicketFieldsTotal11(st, ticket, 0);
                st.setInt(11, id);
                st.setString(12, this.ownername);
                int count = st.executeUpdate();
                log.info("UPDATE tickets SET name = ?, c_x = ?, c_y = ?, price = ?, type = CAST(? AS ticket_type), p_height = ?, p_passport = ?, p_l_x = ? , p_l_y = ?, p_l_z = ?, p_l_name = ? WHERE id = ? AND ownername = ?");
                if (count == 0) {
                    throw NoSuchEntryException.withId(id);
                }
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (st != null) {
                    if (var4 != null) {
                        try {
                            st.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        st.close();
                    }
                }

            }

        } catch (SQLException var16) {
            SQLException e = var16;
            e.printStackTrace();
            throw new NoSuchEntryException(e.getMessage());
        }
    }

    public void clear() {
        try {
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM tickets WHERE ownername = ?");
            Throwable var2 = null;

            try {
                st.setString(1, this.ownername);
                st.executeUpdate();
                log.info("DELETE FROM tickets WHERE ownername = ?");
            } catch (Throwable var12) {
                var2 = var12;
                throw var12;
            } finally {
                if (st != null) {
                    if (var2 != null) {
                        try {
                            st.close();
                        } catch (Throwable var11) {
                            var2.addSuppressed(var11);
                        }
                    } else {
                        st.close();
                    }
                }

            }

        } catch (SQLException var14) {
            SQLException e = var14;
            throw new IllegalStateException(e);
        }
    }

    public void removeEntryWithKey(String key) throws NoSuchEntryException {
        try {
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM tickets WHERE key = ? AND ownername = ?");
            Throwable var3 = null;

            try {
                st.setString(1, key);
                st.setString(2, this.ownername);
                int count = st.executeUpdate();
                log.info("DELETE FROM tickets WHERE key = ? AND ownername = ?");
                if (count == 0) {
                    throw NoSuchEntryException.withKey(key);
                }
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (st != null) {
                    if (var3 != null) {
                        try {
                            st.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        st.close();
                    }
                }

            }

        } catch (SQLException var15) {
            SQLException e = var15;
            throw new IllegalStateException(e);
        }
    }

    public void removeAllThoseLessThan(TicketEntry given) {
        try {
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM tickets WHERE id < ? AND ownername = ?");
            Throwable var3 = null;

            try {
                st.setInt(1, given.id());
                st.setString(2, this.ownername);
                st.executeUpdate();
                log.info("DELETE FROM tickets WHERE id < ? AND ownername = ?");
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (st != null) {
                    if (var3 != null) {
                        try {
                            st.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        st.close();
                    }
                }

            }

        } catch (SQLException var15) {
            SQLException e = var15;
            throw new IllegalStateException(e);
        }
    }

    public void removeAllThoseWithKeyLessThan(String given) {
        try {
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM tickets WHERE key < ? AND ownername = ?");
            Throwable var3 = null;

            try {
                st.setString(1, given);
                st.setString(2, this.ownername);
                st.executeUpdate();
                log.info("DELETE FROM tickets WHERE key < ? AND ownername = ?");
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (st != null) {
                    if (var3 != null) {
                        try {
                            st.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        st.close();
                    }
                }

            }

        } catch (SQLException var15) {
            SQLException e = var15;
            throw new IllegalStateException(e);
        }
    }

    public int countOfEntriesWithPersonGreaterThan(Person given) {
        try {
            PreparedStatement st = this.conn.prepareStatement("SELECT COUNT(*) FROM tickets WHERE p_height > ? AND ownername = ?");
            Throwable var3 = null;

            int var5;
            try {
                st.setInt(1, given.height());
                st.setString(2, this.ownername);
                ResultSet result = st.executeQuery();
                log.info("SELECT COUNT(*) FROM tickets WHERE p_height > ? AND ownername = ?");
                result.next();
                var5 = result.getInt(1);
            } catch (Throwable var15) {
                var3 = var15;
                throw var15;
            } finally {
                if (st != null) {
                    if (var3 != null) {
                        try {
                            st.close();
                        } catch (Throwable var14) {
                            var3.addSuppressed(var14);
                        }
                    } else {
                        st.close();
                    }
                }

            }

            return var5;
        } catch (SQLException var17) {
            SQLException e = var17;
            throw new IllegalStateException(e);
        }
    }

    public Collection<TicketEntry> entriesWithTypeGreaterThan(TicketType given) {
        try {
            PreparedStatement st = this.conn.prepareStatement("SELECT * FROM tickets WHERE type > ? AND ownername = ?");
            Throwable var3 = null;

            try {
                st.setString(1, given.toString());
                st.setString(2, this.ownername);
                ResultSet result = st.executeQuery();
                log.info("SELECT * FROM tickets WHERE type > ? AND ownername = ?");
                List<TicketEntry> entries = new ArrayList();

                while(result.next()) {
                    entries.add(this.createTicketEntry(result));
                }

                return entries;
            } catch (Throwable var16) {
                var3 = var16;
                throw var16;
            } finally {
                if (st != null) {
                    if (var3 != null) {
                        try {
                            st.close();
                        } catch (Throwable var15) {
                            var3.addSuppressed(var15);
                        }
                    } else {
                        st.close();
                    }
                }

            }
        } catch (SQLException var18) {
            SQLException e = var18;
            throw new IllegalStateException(e);
        }
    }

    private TicketEntry createTicketEntry(ResultSet result) throws SQLException {
        TicketEntry ticket = new TicketEntry(result.getInt("id"), result.getDate("creationDate"), new Ticket(result.getString("name"), Coordinates.builder().x(result.getDouble("c_x")).y(result.getDouble("c_y")).build(), result.getInt("price"), this.type(result.getString("type")), Person.builder().height(result.getInt("p_height")).passportId(result.getString("p_passport")).location(Location.builder().x((float)result.getDouble("p_l_x")).y(result.getDouble("p_l_y")).z((float)result.getDouble("p_l_z")).name(result.getString("p_l_name")).build()).build()));

        try {
            ticket.validate().throwExceptionIfFailed();
            return ticket;
        } catch (InvalidObjectException var4) {
            InvalidObjectException e = var4;
            throw new IllegalStateException(e);
        }
    }

    private void setTicketFieldsTotal11(PreparedStatement st, Ticket ticket, int offset) throws SQLException {
        st.setString(offset + 0, ticket.name());
        st.setDouble(offset + 1, ticket.coordinates().x());
        st.setDouble(offset + 2, ticket.coordinates().y());
        st.setObject(offset + 3, ticket.price().orElse(null));
        st.setString(offset + 4, ticket.type().name());
        st.setInt(offset + 5, ticket.person().height());
        st.setString(offset + 6, ticket.person().passportId());
        st.setDouble(offset + 7, (double)ticket.person().location().x());
        st.setDouble(offset + 8, ticket.person().location().y());
        st.setDouble(offset + 9, (double)ticket.person().location().z());
        st.setString(offset + 10, ticket.person().location().name());
        System.out.println(st);
    }

    private TicketType type(String string) {
        switch (string) {
            case "VIP":
                return TicketType.VIP;
            case "USUAL":
                return TicketType.USUAL;
            case "CHEAP":
                return TicketType.CHEAP;
            default:
                throw new IllegalArgumentException("No such type: " + string);
        }
    }
}
