package model;

import java.io.Serializable;

/**
 * Created by egor on 9.2.17.
 */
public class User implements Serializable, Comparable<User>{
    private int id;
    private String name;
    private String lastName;
    private String password;

    public User() {
    }

    public User(String name, String lastName, String password) {
        this.id++;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }

    public User(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public User(int id, String name, String lastName, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User {id = " + this.id + "; name = " +
                this.name + "; Last name = " + this.lastName + "; Password = " + this.password + "}";
    }


    public int compareTo(User user) {
        return getId() - user.getId();
    }
}
