package ru.maltsev.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "posts", indexes = {
        @Index(name = "posts_label_uindex", columnList = "label", unique = true)
})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date", nullable = false)
    private String date;

    public Post() {

    }

    public Post(User user, String label, String text, String date) {
        this.user = user;
        this.label = label;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}