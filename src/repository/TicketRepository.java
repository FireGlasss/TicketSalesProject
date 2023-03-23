package repository;

import connection.Connections;
import entity.ticket.Place;
import entity.ticket.Ticket;
import entity.user.User;

import java.sql.*;

public class TicketRepository {
    private UserRepository userRepository;

    public void createPlace(Place place) {
        try {
            String insert = "INSERT INTO avia_tickets.place(country, city, airport, time_on_ticket) VALUES (?,?,?,?);";
            PreparedStatement prSt = Connections.getDbConnection().prepareStatement(insert);
            prSt.setString(1, place.getCountry());
            prSt.setString(2, place.getCity());
            prSt.setString(3, place.getAirportCode());
            prSt.setObject(4, place.getDate());
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void create(Ticket ticket) {
        try {
            String insert = "INSERT INTO avia_tickets.ticket(arrivalID, departureID, cost) VALUES (?,?,?);";
            PreparedStatement prSt = Connections.getDbConnection().prepareStatement(insert);
            prSt.setInt(1, getPlaceID(ticket.getArrivalData()));
            prSt.setInt(2, getPlaceID(ticket.getDepartureData()));
            prSt.setDouble(3, ticket.getCost());
            prSt.executeUpdate();
            ticket.setID(getTicketID(ticket));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public Ticket getTicket(int ID) {
        try {
            String select = "SELECT place.country, place.city, place.airport,\n" +
                    "place.time_on_ticket from avia_tickets.place\n" +
                    "inner join avia_tickets.ticket on avia_tickets.place.id = avia_tickets.ticket.arrivalID or \n" +
                    "avia_tickets.place.id = avia_tickets.ticket.departureID where avia_tickets.ticket.id = ?;";
            PreparedStatement prSt = Connections.getDbConnection().prepareStatement(select);
            prSt.setInt(1,ID);
            ResultSet resultSet = prSt.executeQuery();
            resultSet.next();

//        Ticket ticket = new Ticket(resultSet.getString("")));
//        return user;
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void update(Ticket ticket) {
        try {
            String change = "UPDATE avia_tickets.ticket SET arrivalID = '?', departureID = '?', cost = ? " +
                    "where id = ?";
            PreparedStatement prSt = Connections.getDbConnection().prepareStatement(change);
//        prSt.setString(1, ticket.getEmail());
//        prSt.setString(2, user.getPassword());
//        prSt.setDouble(3, user.getBalance());
//        prSt.setString(4, String.valueOf(getID(user)));
//        prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void delete(int ID) {
        try {
            String remove = "DELETE FROM avia_tickets.ticket WHERE id = '" + ID + "';";
            Statement st = Connections.getDbConnection().createStatement();
            st.executeUpdate(remove);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void buyTicket(User user, Ticket ticket) {
        try {
            String update = "UPDATE avia_tickets.ticket SET usersID = ? WHERE id = ?;";
            PreparedStatement prSt = Connections.getDbConnection().prepareStatement(update);
            prSt.setInt(1, userRepository.getID(user));
            prSt.setInt(2, getTicketID(ticket));
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public int getPlaceID(Place place) {
        try {
            String ID = "SELECT id from avia_tickets.place where city = '" + place.getCity() + "';";
            Statement st = Connections.getDbConnection().createStatement();
            ResultSet resultSet = st.executeQuery(ID);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public int getTicketID(Ticket ticket) {
        try {
            String ID = "SELECT id from avia_tickets.ticket where cost = '" + ticket.getCost() + "';";
            Statement st = Connections.getDbConnection().createStatement();
            ResultSet resultSet = st.executeQuery(ID);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

}