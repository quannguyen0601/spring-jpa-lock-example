package com.quan.nguyen.net.java.example.lock;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerAdviceConfig {

    @ExceptionHandler(StaleObjectStateException.class)
    ResponseEntity<String> handleErrorStaleObjectDuplate(){
        return ResponseEntity.badRequest().body("THis Data has change");
    }
}
