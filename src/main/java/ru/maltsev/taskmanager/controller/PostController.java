package ru.maltsev.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maltsev.taskmanager.model.Post;
import ru.maltsev.taskmanager.service.PostService;
import ru.maltsev.taskmanager.utility.PostRepository;
import ru.maltsev.taskmanager.utility.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController()
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(path = "/all",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getAllPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPostById(@PathVariable("id") int id) {
        Optional<Post> postData = postRepository.findById(id);
        return postData
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/user/{user_id}/post",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> createPost(@PathVariable(value = "user_id") Integer user_id, @RequestBody Post post) {
        try {
            return new ResponseEntity<>(userRepository.findById(user_id).map(user -> {
                post.setUser(user);
                return postRepository.save(post);
            }).get(), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/user/{user_id}/post/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> editPost(
            @PathVariable("user_id") int user_id,
            @PathVariable("id") int id,
            @RequestBody Post postEdited) {
        if (!userRepository.existsById(user_id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postRepository.findById(id).map(post -> {
            post.setText(postEdited.getText());
            post.setLabel(postEdited.getLabel());
            return postRepository.save(post);
        }).get(), HttpStatus.OK);
    }
}
