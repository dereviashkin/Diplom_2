package entities;

public class User {

    private String email;
    private String password;
    private String name;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}