//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package server.advanced.persistence;

import server.advanced.persistence.exception.IncorrectPasswordException;
import server.advanced.persistence.exception.UserAlreadyExistsException;
import server.advanced.persistence.exception.UserNotFoundException;
import server.simple.persistence.repository.basement.TicketRepository;

public interface AuthService {
    void register(String var1, String var2) throws UserAlreadyExistsException;

    TicketRepository ticketRepository(String var1, String var2) throws UserNotFoundException, IncorrectPasswordException;
}
