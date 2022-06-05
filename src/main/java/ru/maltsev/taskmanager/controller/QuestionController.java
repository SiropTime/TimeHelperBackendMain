package ru.maltsev.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maltsev.taskmanager.model.Question;
import ru.maltsev.taskmanager.utility.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController()
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/all",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = new ArrayList<>();
            questionRepository.findAll().forEach(questions::add);
            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") long id) {
        Optional<Question> questionData = questionRepository.findById(id);
        // Если поиск удался (объект в Optional существует) отправляем, иначе 404
        return questionData
                .map(question -> new ResponseEntity<>(question, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        try {
            Question q = questionRepository
                    .save(new Question(question.getQuestion(), question.getAnswer()));
            return new ResponseEntity<>(q, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/edit/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") long id, @RequestBody Question question) {
        Optional<Question> questionData = questionRepository.findById(id);
        if (questionData.isPresent()) {
            Question q = questionData.get();
            q.setQuestion(question.getQuestion());
            q.setAnswer(question.getAnswer());
            return new ResponseEntity<>(questionRepository.save(q), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
