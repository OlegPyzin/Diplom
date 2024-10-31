package com.example.diplom.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice

public class GlobalException {
    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                return super.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults()
                        .including(ErrorAttributeOptions.Include.MESSAGE));
            }
        };
    }

    @ExceptionHandler(CustomException.class)
    public void handleCustomException(HttpServletResponse response, CustomException exception) throws IOException {
        response.sendError(exception.getHttpStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorMessage> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        String parameterName = exception.getParameterName();

        log.error("{} необходимый параметр отсутствует.", parameterName);

        return ResponseEntity.status(400)
                .body(new ErrorMessage(String.format("Необходимый параметр отсутствует: %s", parameterName)));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        String parameterName = exception.getParameter().getParameterName();
        Class<?> parameterType = exception.getParameter().getParameterType();

        log.error("{} {} несоответствие типа переданных данных.", parameterName, parameterType);

        return ResponseEntity.status(400)
                .body(new ErrorMessage(String.format("Несоответствие типа переданных данных: [%s]", parameterName)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) throws IOException {

        FieldError fieldError = exception.getFieldError();

        String message = fieldError != null ? fieldError.getDefaultMessage() : exception.getMessage();

        return ResponseEntity.status(400)
                .body(new ErrorMessage(message));
    }
}
