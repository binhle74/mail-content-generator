package me.binhle.mailgenerator.ext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.MissingFormatArgumentException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, MissingFormatArgumentException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleIllegalArgumentException(Exception exception, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(((ServletWebRequest)webRequest).getRequest().getRequestURI());
        errorMessage.setTimestamp(new Date());
        return errorMessage;
    }

    static class ErrorMessage {
        private String message;
        private String path;
        private Date timestamp;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Date getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }
    }
}
