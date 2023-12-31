package org.example.web.controllers;

import org.example.app.exceptions.BookShelfLoginException;
import org.example.app.exceptions.UploadFileException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class ErrorController {

    @GetMapping("/404")
    public String notFoundError(){
        return "errors/404";
    }

    @ExceptionHandler(BookShelfLoginException.class)
    public String handlerError(Model model, BookShelfLoginException exception){
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/404";
    }

    @ExceptionHandler(UploadFileException.class)
    public String handlerError(Model model, UploadFileException exception){
        model.addAttribute("errorMessage", exception.getMessage());
        return "errors/500";
    }

}

