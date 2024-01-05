package org.user;

import lombok.Data;

@Data
public class User {
    private String account;
    private String name;

    private int identity;
    private String password;

    public User(){}
    public User(String account, String name, int identity, String password) {
        this.account = account;
        this.name = name;
        this.identity = identity;
        this.password = password;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}