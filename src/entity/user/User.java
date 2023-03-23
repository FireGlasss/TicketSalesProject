package entity.user;
import entity.ticket.Ticket;
import java.util.ArrayList;
public class User {
    private String email;
    private String password;
    private double balance;
    private ArrayList<Ticket> tickets;
    private int ID;

    public User(String email, String password, double balance) {
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public void buyTicket(Ticket ticket) {
        if (ticket.getCost() <= balance) {
            balance -= ticket.getCost();
        } else {
            System.out.println("Недостаточно средств");
        }
        tickets.add(ticket);
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setPassword (String newPassword) {
        password = newPassword;
    }
    public void setEmail (String newEmail) {
        email = newEmail;
    }
    public void increaseBalance(double amount) {
        this.balance = Double.sum(this.balance, amount);
    }
    public void takeFlight(Ticket ticket) {
        tickets.remove(ticket);
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
