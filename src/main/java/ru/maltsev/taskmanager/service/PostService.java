package ru.maltsev.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maltsev.taskmanager.model.Post;
import ru.maltsev.taskmanager.model.User;
import ru.maltsev.taskmanager.utility.PostRepository;
import ru.maltsev.taskmanager.utility.UserRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post addPost(Post post) {
        User user = userRepository.findById(post.getUser().getId()).orElse(null);
        if (user == null) {
            user = new User();
        }
        user.setUsername(post.getUser().getUsername());
        post.setUser(user);
        return postRepository.save(post);
    }

    public Post editPosts(Post post) {
        return postRepository.save(post);
    }

    public void deletePosts(Integer id) {
        postRepository.deleteById(id);
    }

}
