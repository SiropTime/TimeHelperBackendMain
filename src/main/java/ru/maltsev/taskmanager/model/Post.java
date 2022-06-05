package ru.maltsev.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts", indexes = {
        @Index(name = "posts_label_uindex", columnList = "label", unique = true)
})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date", nullable = false)
    private String date = new Date().toString();

    public Post(User user, String label, String text, String date) {
        this.user = user;
        this.label = label;
        this.text = text;
        this.date = date;
    }

}