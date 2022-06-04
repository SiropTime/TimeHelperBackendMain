package ru.maltsev.taskmanager.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InteractionException extends Exception {

    public InteractionException() {
        super("Произошла ошибка! Попробуйте использовать другой тип данных!");
    }

}
