package org.example.exam.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    public Member() {}

    public Member(String userId, String password, boolean isActive) {
        this.userId = userId;
        this.password = password;
        this.isActive = isActive;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.setMember(this);
    }
}
