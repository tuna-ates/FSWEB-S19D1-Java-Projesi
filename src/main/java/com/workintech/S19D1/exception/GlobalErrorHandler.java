package com.workintech.S19D1.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {
    @ExceptionHandler
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(ApiException apiException){
        ApiExceptionResponse apiExceptionResponse=new ApiExceptionResponse(apiException.getMessage());
        return new ResponseEntity<>(apiExceptionResponse, apiException.getHttpStatus());
    }

//    @ExceptionHandler
//    public ResponseEntity exceptionHandler(MethodArgumentNotValidException exception){
//        List errorList=exception.getBindingResult().getFieldErrors().stream()
//                .map(fieldError -> {
//                    Map<String,String> errorMap=new HashMap<>();
//                    errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
//                    return errorMap;
//                }).collect(Collectors.toList());
//       return new ResponseEntity<>(errorList,HttpStatus.BAD_REQUEST);
//    }
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getFieldErrors()
            .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
    return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
}

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


    @ExceptionHandler
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(Exception exception){
        ApiExceptionResponse apiExceptionResponse=new ApiExceptionResponse(exception.getMessage());
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
