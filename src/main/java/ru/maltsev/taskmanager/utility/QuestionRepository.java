package ru.maltsev.taskmanager.utility;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maltsev.taskmanager.model.Question;

public interface QuestionRepository  extends JpaRepository<Question, Long> {



}
