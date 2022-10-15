package me.binhle.mailgenerator.exception;

public class MissingParameterValueException extends RuntimeException {
    public MissingParameterValueException(String parameterName) {
        super("Missing parameter value for '" + parameterName + "'");
    }
}
