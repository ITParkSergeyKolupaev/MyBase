package ru.itpark.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itpark.exception.*;

@ControllerAdvice
public class AppControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LinkNotFoundException.class)
    public String linkNotFoundError(Model model) {
        model.addAttribute("message", "Ссылка не найдена на тему");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DbException.class)
    public String dDbError(Model model) {
        model.addAttribute("message", "Error");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TopicNotFoundException.class)
    public String topicNotFoundError(Model model) {
        model.addAttribute("message", "Ссылка не найдена на нужную ссылку");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MultimediaNotFoundException.class)
    public String multimediaNotFoundError(Model model) {
        model.addAttribute("message", "Ссылка не найдена на медиа");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NameIsNullException.class)
    public String nameIsNullError(Model model) {
        model.addAttribute("message", "Не заполнено текстовое поле");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnsupportedFileContentTypeException.class)
    public String unsupportedFileContentTypeError(Model model) {
        model.addAttribute("message", "Формат картинок jpg и png");
        return "error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UploadFileException.class)
    public String uploadFileError(Model model) {
        model.addAttribute("message", "Не удалось загрузить файл");
        return "error";
    }
}
