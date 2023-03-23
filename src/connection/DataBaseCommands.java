package connection;

public class DataBaseCommands {

    public static final String CREATE_DATA_BASE = "CREATE DATABASE IF NOT EXISTS avia_tickets;";
    public static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " +
            "avia_tickets.users(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
            "email VARCHAR(30) NOT NULL, password VARCHAR(30) NOT NULL, " +
            "balance DOUBLE, ticketID INT);";
    public static final String CREATE_TABLE_PLACE = "CREATE TABLE IF NOT EXISTS " +
            "avia_tickets.place(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
            "country VARCHAR(30) NOT NULL, city VARCHAR(30) NOT NULL, " +
            "airport VARCHAR(45) NOT NULL, time_on_ticket DATETIME);";
    public static final String CREATE_TABLE_TICKET = "CREATE TABLE IF NOT EXISTS " +
            "avia_tickets.ticket(id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
            "arrivalID INT, departureID INT, cost DOUBLE NOT NULL, usersID INT, " +
            "FOREIGN KEY(arrivalID) REFERENCES place(id), " +
            "FOREIGN KEY(departureID) REFERENCES place(id), " +
            "FOREIGN KEY(usersID) REFERENCES users(id));";

    public static final String DROP_DATABASE = "DROP DATABASE avia_tickets";



}
