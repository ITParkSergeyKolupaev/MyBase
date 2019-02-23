package ru.itpark.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itpark.exception.TopicNotFoundException;

@ControllerAdvice
public class AppControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TopicNotFoundException.class)
    public String handleProductNotFound(Model model) {
        model.addAttribute("message", "Not found");
        return "error";
    }
}
