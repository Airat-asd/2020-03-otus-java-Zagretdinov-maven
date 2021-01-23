package ru.otus.businessLayer.model;

import javax.persistence.*;

@Entity
@Table(name = "tUsers")
public class User {

    @Id
    private String login = "";

    @Column(name = "name")
    private String name = "";

    @Column(name = "passwordHash")
    private int passwordHash;

    @Column(name = "isAnAdministrator")
    private char isAnAdministrator = 'n';

    public User() {
    }

    public User(String login, String name, int passwordHash, char isAnAdministrator) {
        this.login = login;
        this.name = name;
        this.passwordHash = passwordHash;
        this.isAnAdministrator = isAnAdministrator;
    }

    public User(String login, String name, int passwordHash) {
        this.login = login;
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login != null) {
            this.login = login;
        }
    }

    public char getIsAnAdministrator() {
        return isAnAdministrator;
    }

    public void setIsAnAdministrator(char isAnAdministrator) {
        if (isAnAdministrator == 'y' || isAnAdministrator == 'n') {
            this.isAnAdministrator = isAnAdministrator;
        }
    }

    public int getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(int passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "User{" +
                "login=" + login +
                ", name=" + name +
                ", passwordHash=" + passwordHash +
                ", administrator=" + isAnAdministrator +
                '}';
    }
}
