package ru.maltsev.taskmanager.utility;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maltsev.taskmanager.model.Question;
import ru.maltsev.taskmanager.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIsAdmin(boolean isAdmin);
    User findUserByUsername(String username);

}
