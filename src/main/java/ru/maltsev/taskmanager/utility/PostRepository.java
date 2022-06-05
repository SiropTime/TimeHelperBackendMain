package ru.maltsev.taskmanager.utility;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maltsev.taskmanager.model.Post;
import ru.maltsev.taskmanager.model.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findPostsByUser(User user);
    Post findPostByLabel(String label);

}
