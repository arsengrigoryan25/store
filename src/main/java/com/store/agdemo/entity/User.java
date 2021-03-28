package com.store.agdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class User extends IdentifiedEntity<Long> {
    @Column
    private String username;
    @Column
    private String password;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @OneToMany(mappedBy = "user")
    private Set<Rate> rates;

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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public enum Role{
        ADMIN, USER
    }
}
