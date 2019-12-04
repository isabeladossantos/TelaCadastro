package br.isa.data.entities;

public class User {
    protected int id;
    protected String username;
    protected String name;
    protected String email;
    protected String password;
    protected boolean superuser;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getSuperuser() {
        return this.superuser;
    }

    public void setSuperuser(Boolean superuser) {
        this.superuser = superuser;
    }

    public String toString() {
        return "Pessoa:[id=" + this.getClass() + ", username=" + this.getUsername()
                + ", name=" + this.getName() + ", email=" + this.getEmail()
                + ", superuser=" + this.getSuperuser();
    }

}