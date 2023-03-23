package service;

import entity.ticket.Ticket;
import entity.user.User;
import repository.TicketRepository;
import repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private TicketRepository ticketRepository = new TicketRepository();

    public void changePassword(String email, String password) throws SQLException, ClassNotFoundException {

        User user = userRepository.getUser(email);

        user.setPassword(password);

        userRepository.update(user);
    }

    public void changeEmail(String email, String newEmail) throws SQLException, ClassNotFoundException {

        User user = userRepository.getUser(email);

        user.setEmail(newEmail);

        userRepository.update(user);
    }

    public void increaseBalance(String email, double amount) throws SQLException, ClassNotFoundException {
        User user = userRepository.getUser(email);
        user.increaseBalance(amount);
        userRepository.update(user);
    }

    public void deleteUser(String email) {
        userRepository.delete(email);
    }

    public void createUser(String email, String password, double balance) {
        User user = new User(email, password, balance);
        userRepository.create(user);
        System.out.println("Пользователь создан!");
    }

    public void buyTicket(String email, Ticket ticket) throws SQLException, ClassNotFoundException {
        User user = userRepository.getUser(email);
        user.buyTicket(ticket);
        userRepository.update(user);
    }
}

//    public void changeValuta(String email, Valuta valuta) {
//        User user = userRepository.getUser(email);
//        user.changeValuta(valuta);
//        userRepository.update(user);
//    }