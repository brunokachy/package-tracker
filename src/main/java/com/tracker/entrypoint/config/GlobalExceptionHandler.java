package com.tracker.entrypoint.config;

import com.tracker.core.exception.BadRequestException;
import com.tracker.core.exception.NotFoundException;
import com.tracker.entrypoint.models.response.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public ResponseEntity<ApiResponse<String>> handleException(Exception exception) {
        log.info("Exception: {}", ExceptionUtils.getStackTrace(exception));
        ApiResponse<String> apiResponse = new ApiResponse<>("Sorry, currently unable to process request at the moment.");
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class, ConstraintViolationException.class})
    public ResponseEntity<ApiResponse<String>> handleMissingServletRequestParameterException(Exception exception) {
        log.info("Exception: {}", exception.getLocalizedMessage());
        ApiResponse<String> apiResponse = new ApiResponse<>(exception.getLocalizedMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseEntity<ApiResponse<String>> handleMissingServletRequestPartException(Exception exception) {
        log.info("error message: {}", exception.getMessage());
        ApiResponse<String> apiResponse = new ApiResponse<>("Request validation failure. Please check your request data.");
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exception) {
        String errorMessage = "Request validation failure. Please check your request data.";
        BindingResult result = exception.getBindingResult();
        FieldError fieldError = result.getFieldError();
        if (fieldError != null) {
            errorMessage = fieldError.getDefaultMessage();
        }
        log.info("error message: {}", errorMessage);
        ApiResponse<String> apiResponse = new ApiResponse<>(errorMessage);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequestException(BadRequestException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponse<String> apiResponse = new ApiResponse<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFoundException(NotFoundException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponse<String> apiResponse = new ApiResponse<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

}
