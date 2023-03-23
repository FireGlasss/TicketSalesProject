package main;

import connection.Connections;
import connection.DataBaseCommands;
import entity.ticket.Place;
import entity.ticket.Ticket;
import entity.user.User;
import repository.TicketRepository;
import repository.UserRepository;
import service.TicketService;
import service.UserService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    private static void PrintMenu() {
        System.out.println("1 - Создать профиль");
        System.out.println("2 - Найти профиль");
        System.out.println("3 - Изменить профиль");
        System.out.println("4 - Удалить профиль");
        System.out.println("5 - Создать местность");
        System.out.println("6 - Создать билет");
        System.out.println("7 - Найти билет");
        System.out.println("8 - Изменить билет");
        System.out.println("9 - Удалить билет");
        System.out.println("0 - Закрыть программу");

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connections.startConnection();
        UserService userService = new UserService();
        System.out.println("Создание произошло успешно!");
        User user1 = new User("234@arg", "1111", 10000);
        UserRepository us = new UserRepository();
        TicketRepository tr = new TicketRepository();
        Place[] place = new Place[2];
        Ticket ticket;
        Scanner sc = new Scanner(System.in);
        PrintMenu();
        try {
            int chose = Integer.parseInt(sc.nextLine());
            int i = 0;
            while (!(chose == 0)) {
                if (chose == 1) {
                    System.out.print("Пожалуйста, введите свою почту - ");
                    String email = sc.nextLine();
                    System.out.print("\n" + "Пожайлуста, введите пароль - ");
                    String password = sc.nextLine();
                    System.out.print("\n" + "Пожайлуста, введите на какую сумму хотите пополнить счет - ");
                    double balanceSum = sc.nextDouble();

                    user1 = new User(email, password, balanceSum);

                    us.create(user1);
                    System.out.println("Пользователь создан!");
                }
                if (chose == 2) {
                    user1 = us.getUser("23524@fdgd");
                }
                if (chose == 3) {
                    userService.changeEmail("54645", "21212");
                    System.out.println("Успех");
//                    us.update(user1);
                }
                if (chose == 4) {
                    us.delete("54645");
                }
                if (chose == 5) {
                    System.out.print("Страна - ");
                    String county = sc.nextLine();
                    System.out.print("\n" + "Город - ");
                    String city = sc.nextLine();
                    System.out.print("\n" + "Аэропорт - ");
                    String airPort = sc.nextLine();
                    System.out.print("\n" + "Дата и время - ");
                    LocalDateTime localDateTime = LocalDateTime.now();
                    place[i] = new Place(county,city,airPort, localDateTime);
                    System.out.println(place[i].getDate());
                    tr.createPlace(place[i]);

                }
                if (chose == 6) {
                    ticket = new Ticket(place[0], place[1], sc.nextDouble());
                    tr.create(ticket);
                } if (chose == 7) {
                } if (chose == 8) {
                } if (chose == 9) {
                    tr.delete(1);
                }
                if (chose == 10) {

                }
                i++;
                System.out.println();
                PrintMenu();
                chose = sc.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Введены не коррктные данные, перезапустите программу");
            throw e;
        }
    }
}