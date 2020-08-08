/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


public class User {
    private int id;
    private String username;
    private String password;
    private Yetki yetki;
    
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.yetki = new Yetki();
    }

    public User(int id, String username, String password,Yetki yetki) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.yetki = yetki;
    }
    public User(String username, String password,Yetki yetki) {
        this.username = username;
        this.password = password;
        this.yetki = yetki;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Yetki getYetki() {
        return yetki;
    }

    public void setYetki(Yetki yetki) {
        this.yetki = yetki;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
