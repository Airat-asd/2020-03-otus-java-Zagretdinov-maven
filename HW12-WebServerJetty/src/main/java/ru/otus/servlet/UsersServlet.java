package ru.otus.servlet;


import ru.otus.businessLayer.service.DBServiceUser;
import ru.otus.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class UsersServlet extends HttpServlet {

    private static final String USERS_PAGE_TEMPLATE = "users.html";
    private static final String TEMPLATE_ATTR_ALL_USER = "listOfUsers";

    private final DBServiceUser dbServiceUser;
    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor, DBServiceUser dbServiceUser) {
        this.templateProcessor = templateProcessor;
        this.dbServiceUser = dbServiceUser;
    }

    /*
    <tr>
                <td>${randomUser.id}</td>
                <td>${randomUser.name}</td>
                <td>${randomUser.login}</td>
                <td>${randomUser.password}</td>
            </tr>
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        final String[] param = new String[1];
        dbServiceUser.getAllUsers().ifPresent(allUsers -> allUsers.forEach(user -> param[0] =
                String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                        user.getName(), user.getLogin(), user.getPasswordHash())));
        paramsMap.put(TEMPLATE_ATTR_ALL_USER, param);

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

}
