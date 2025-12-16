package com.vekrest.vekproducer.vekproducer.controller.advice;

import com.vekrest.vekproducer.vekproducer.controller.advice.exception.BadRequestException;
import com.vekrest.vekproducer.vekproducer.controller.advice.exception.InternalServerException;
import com.vekrest.vekproducer.vekproducer.controller.advice.exception.NotFoundException;
import com.vekrest.vekproducer.vekproducer.controller.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerException.class, Exception.class})
    public ErrorResponse handleInternalServerError(
            Exception ex,
            HttpServletRequest req
    ) {
        LOG.error("HANDLE ERROR ON INTERNAL_SERVER_ERROR", ex);

        return new ErrorResponse(
                LocalDateTime.now(),
                req.getServletPath(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundError(
            NotFoundException ex,
            HttpServletRequest req
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                req.getServletPath(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public ErrorResponse handleNoResourceFoundException(
            NoResourceFoundException ex,
            HttpServletRequest req
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                req.getServletPath(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequest(
            BadRequestException ex,
            HttpServletRequest req
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                req.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                ex.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest req
    ) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors()
                .forEach(v -> errors.add(v.getDefaultMessage()));

        return new ErrorResponse(
                LocalDateTime.now(),
                req.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                errors.toString()
        );
    }

    @ResponseBody
    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ErrorResponse handleHttpMediaTypeNotAcceptableException(
            BadRequestException ex,
            HttpServletRequest req
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                req.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                ex.getMessage()
        );
    }
}