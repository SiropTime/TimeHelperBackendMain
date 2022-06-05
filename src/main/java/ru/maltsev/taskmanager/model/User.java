package ru.maltsev.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "users_username_uindex", columnList = "username", unique = true),
        @Index(name = "users_email_uindex", columnList = "email", unique = true)
})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Post> posts = new LinkedHashSet<>();


}