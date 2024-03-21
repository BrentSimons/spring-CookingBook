package be.continuum.cookingbook.configuration;

import be.continuum.cookingbook.exception.BadRequestException;
import be.continuum.cookingbook.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFound(NotFoundException notFoundException) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity badRequest(BadRequestException badRequestException) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        String errorMessage = "";

        for (ObjectError error : allErrors) {
            errorMessage += error.getDefaultMessage() + "\n";
        }

        return ResponseEntity.badRequest().body(errorMessage);
    }
}
