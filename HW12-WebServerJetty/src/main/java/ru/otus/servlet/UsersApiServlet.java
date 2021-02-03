package ru.otus.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;

import java.io.IOException;

public class UsersApiServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UsersApiServlet.class);

    private final DBServiceUser dbServiceUser;

    public UsersApiServlet(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = dbServiceUser.saveUser(getNewUserFromRequest(request));
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.print(message);
        logger.info("Сообщение \"{}\" клиенту отправлено", message);
    }

    private User getNewUserFromRequest(HttpServletRequest request) {
        User user;
        String[] path = request.getPathInfo().split("/");
        String[] nameLoginPassword = path[1].split("&");
        if (nameLoginPassword.length < 3) {
            user = new User();
        } else if (nameLoginPassword[0].isEmpty() || nameLoginPassword[1].isEmpty() || nameLoginPassword[2].isEmpty()) {
            user = new User();
        } else {
            user = new User(nameLoginPassword[0], nameLoginPassword[1], nameLoginPassword[2].hashCode());
        }
        return user;
    }
}
