package ru.maltsev.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "users_username_uindex", columnList = "username", unique = true),
        @Index(name = "users_email_uindex", columnList = "email", unique = true)
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "isAdmin", nullable = false)
    private Boolean isAdmin = false;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<Post> posts = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

}