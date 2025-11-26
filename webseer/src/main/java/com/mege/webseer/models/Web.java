package com.mege.webseer.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "webs")
public class Web {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String url;
    private Integer numberOfAccess;
    private Instant lastAccess;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Web(String id, String url, Integer numberOfAccess, Instant lastAccess, User user) {
        this.id = id;
        this.url = url;
        this.numberOfAccess = numberOfAccess;
        this.lastAccess = lastAccess;
        this.user = user;
    }

    public Web() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNumberOfAccess() {
        return numberOfAccess;
    }

    public void setNumberOfAccess(Integer numberOfAccess) {
        this.numberOfAccess = numberOfAccess;
    }

    public Instant getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Instant lastAccess) {
        this.lastAccess = lastAccess;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
