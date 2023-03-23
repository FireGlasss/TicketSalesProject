package http_connect;

import com.google.gson.Gson;
import entity.ticket.Ticket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import service.UserService;
import java.time.LocalDateTime;


import java.sql.SQLException;
import java.util.HashMap;

public class MainHandler extends ChannelInboundHandlerAdapter {

    private UserService userService = new UserService();

    private final static String CREATE_USER = "create User";
    private final static String DELETE_USER = "delete User";
    private final static String CHANGE_PASSWORD = "change Password";
    private final static String CHANGE_EMAIL = "change Email";
    private final static String BUY_TICKET = "buy Ticket";
    private final static String INCREACE_BALANCE = "increase Balance";


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.print("Подключение произошло");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        StringBuilder stringBuilder = new StringBuilder();
        String request = null;
        while (buf.readableBytes() > 0) {
            stringBuilder.append((char) buf.readByte());
            request = stringBuilder.toString();
        }
        System.out.println(request);
//        String[] key = request.split(": ");
//        if (key.length == 2) {
//            request = key[1];
//            searchOperation(request);
//        }
        if (request.contains(": ")) {
            HashMap <String, String> text = new Gson().fromJson(request, HashMap.class);
            searchOperation(text);
        }
        buf.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public void searchOperation(HashMap <String, String> request) throws SQLException, ClassNotFoundException {
        String operation = request.get("operation");
        switch (operation) {
            case CREATE_USER:
                userService.createUser(request.get("email"), request.get("password"), Double.parseDouble(request.get("balance")));
                System.out.println("Готово");
                break;
            case DELETE_USER:
                userService.deleteUser(request.get("email"));
                System.out.println("Пользователь удален");
                break;
            case CHANGE_PASSWORD:
                userService.changePassword(request.get("password"), request.get("newPassword"));
                System.out.println("Пароль изменен");
                break;
            case CHANGE_EMAIL:
                userService.changeEmail(request.get("email"), request.get("newEmail"));
                System.out.println("Почта изменена");
                break;
            case BUY_TICKET:
                Ticket ticket = new Gson().fromJson(request.get("ticket"), Ticket.class);
                userService.buyTicket(request.get("email"), ticket);
//                LocalDateTime dateTime = new Gson().fromJson(request.get("readDate"), LocalDateTime.class);
                System.out.println("Билет куплен!");
                break;
            case INCREACE_BALANCE:
                userService.increaseBalance(request.get("email"), Double.parseDouble(request.get("summa")));
                System.out.println("Баланс увеличен");
                break;
            default:
                System.out.println("Неизвестная команда");
        }
    }
}
