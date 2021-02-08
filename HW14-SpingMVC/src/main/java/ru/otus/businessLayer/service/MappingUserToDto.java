package ru.otus.businessLayer.dto;

public class DtoUser {
    private String login;

    private String name;

    private String password;

    private char isAnAdministrator = 'n';

    public DtoUser() {
    }

    public DtoUser(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public DtoUser(String login, String name, String password, char isAnAdministrator) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.isAnAdministrator = isAnAdministrator;
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
