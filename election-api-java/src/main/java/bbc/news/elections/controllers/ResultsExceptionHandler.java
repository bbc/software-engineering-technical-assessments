package bbc.news.elections.controllers;

import bbc.news.elections.model.ApiResponse;
import bbc.news.elections.service.NotImplementedException;
import bbc.news.elections.service.ResultNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResultsExceptionHandler {

    @ExceptionHandler(ResultNotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundApiException(
            ResultNotFoundException ex) {
        ApiResponse response = new ApiResponse("not-found-001",String.format("%d not found",ex.getId()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<ApiResponse> handleNotImplementedApiException(
            NotImplementedException ex) {
        ApiResponse response = new ApiResponse("not-implemented-002", "Function not yet implemented");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
