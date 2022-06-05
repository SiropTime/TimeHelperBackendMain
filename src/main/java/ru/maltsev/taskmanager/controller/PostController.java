package ru.maltsev.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maltsev.taskmanager.model.Post;
import ru.maltsev.taskmanager.utility.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController()
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostRepository postRepository;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/all",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = new ArrayList<>();
            postRepository.findAll().forEach(posts::add);
            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPostById(@PathVariable("id") int id) {
        Optional<Post> postData = postRepository.findById(id);
        return postData
                .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/create",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            Post p = postRepository
                    .save(new Post(post.getUser(), post.getLabel(), post.getText(), post.getDate()));
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/edit/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> editPost(@PathVariable("id") int id, @RequestBody Post post) {
        Optional<Post> postData = postRepository.findById(id);
        if (postData.isPresent()) {
            Post p = postData.get();
            p.setLabel(post.getLabel());
            p.setText(post.getText());
            p.setDate(post.getDate());
            p.setUser(post.getUser());
            return new ResponseEntity<>(postRepository.save(p), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
