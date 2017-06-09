package com.spring_backend.domain;


import com.spring_backend.repository.RoleEnumConverter;
import generated.tables.records.UsersRecord;

public class User {
    private long id;
    private String emailid;
    private String password;
    private String name;
    private boolean activated;
    private Role role;

    public User(UsersRecord record) {
        this.id = record.getId();
        this.emailid = record.getEmailid();
        this.password = record.getPasswordHash();
        this.name = record.getName();
        this.activated = record.getActivated();
        this.role = new RoleEnumConverter().from(record.getRole());
    }

    public User() {};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }


    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}