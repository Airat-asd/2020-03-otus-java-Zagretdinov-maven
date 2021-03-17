package ru.otus.businessLayer.dto;

import ru.otus.messagesystem.client.ResultDataType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dto extends ResultDataType {

    private List<Dto> listOfDtoUsers;

    private String login;

    private String name;

    private String password;

    private char isAnAdministrator = 'n';

    public Dto() {
        listOfDtoUsers = new ArrayList<>();
    }

    public Dto(String login) {
        this.login = login;
        listOfDtoUsers = new ArrayList<>();
    }

    public Dto(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public Dto(String login, String name, String password, char isAnAdministrator) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.isAnAdministrator = isAnAdministrator;
    }

    public Dto(List<Dto> listOfDtoUsers) {
        this.listOfDtoUsers = new ArrayList<>(listOfDtoUsers);
    }

    public List<Dto> getListOfDtoUsers() {
        return Collections.unmodifiableList(listOfDtoUsers);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getIsAnAdministrator() {
        return isAnAdministrator;
    }

    public void setIsAnAdministrator(char isAnAdministrator) {
        this.isAnAdministrator = isAnAdministrator;
    }

}
