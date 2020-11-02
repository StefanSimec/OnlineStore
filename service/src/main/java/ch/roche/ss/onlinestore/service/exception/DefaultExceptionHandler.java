package ch.roche.ss.onlinestore.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Default exception handler that hanleds all errors left out by other specific handlers
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class DefaultExceptionHandler
{
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericExcetion(Exception ex)
    {
        log.error("Uncaught exception",ex);
        return "Internal server error";
    }
}
