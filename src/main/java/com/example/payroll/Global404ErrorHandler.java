package com.example.payroll;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class Global404ErrorHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFound(
            EmployeeNotFoundException ex,
            WebRequest request
    ) {
        ErrorDetails errorDetails =
                ErrorDetails
                        .builder()
                        .time(LocalDateTime.now())
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.toString())
                        .path(request.getDescription(false).replace("uri=", ""))
                        .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }
}

@Builder
@Getter
class ErrorDetails {
    private LocalDateTime time;
    private String message;
    private String status;
    private String path;
}


