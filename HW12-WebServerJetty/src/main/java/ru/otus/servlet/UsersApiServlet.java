package ru.otus.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.otus.businessLayer.model.User;
import ru.otus.businessLayer.service.DBServiceUser;

import java.io.IOException;

public class UsersApiServlet extends HttpServlet {

    private final DBServiceUser dbServiceUser;
    private final Gson gson;

    public UsersApiServlet(DBServiceUser dbServiceUser, Gson gson) {
        this.dbServiceUser = dbServiceUser;
        this.gson = gson;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        dbServiceUser.saveUser(getNewUserFromRequest(request));

        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        out.print("User добавлен");

    }

    private User getNewUserFromRequest(HttpServletRequest request) {
        String[] path = request.getPathInfo().split("/");
        String[] nameLoginPassword = path[1].split("&");
        return new User(nameLoginPassword[0],nameLoginPassword[1],nameLoginPassword[2].hashCode());
    }

}
