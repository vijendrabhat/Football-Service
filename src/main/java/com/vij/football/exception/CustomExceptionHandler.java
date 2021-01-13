package com.vij.football.exception;

import java.nio.file.AccessDeniedException;

import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vij.football.response.ErrorResponse;
import com.vij.football.response.FootballResponse;
import com.vij.football.response.FootballServiceResponseMessage;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
    return new ResponseEntity<Object>("Access denied by Football Service", new HttpHeaders(), HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(RecordNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
    FootballServiceResponseMessage res = new FootballServiceResponseMessage(ex.getMessage());
    return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(FootballServiceException.class)
  public final ResponseEntity<Object> handleInternalApiExceptionn(FootballServiceException ex, WebRequest request) {
    return new ResponseEntity<>(prepareResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private FootballResponse prepareResponse(FootballServiceException ex) {
    ErrorResponse errorResponse = new ErrorResponse(0, ex.getMessage(), ex.getUserStackTrace());
    FootballResponse res = new FootballResponse(ex.getUserMessage(), errorResponse);
    return res;
  }

  @ExceptionHandler(ValidationException.class)
  public final ResponseEntity<Object> handleValidationExceptionn(ValidationException ex, WebRequest request) {
    FootballServiceResponseMessage res = new FootballServiceResponseMessage(ex.getMessage());
    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }
}
