package com.mege.webseer.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Web> webList;

    public User(String id, String name, String password, List<Web> webList) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.webList = webList;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Web> getWebList() {
        return webList;
    }

    public void setWebList(List<Web> webList) {
        this.webList = webList;
    }
}
