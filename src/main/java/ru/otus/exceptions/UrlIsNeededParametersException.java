package ru.otus.exceptions;

public class UrlIsNeededParametersException extends RuntimeException {
    public UrlIsNeededParametersException(String text) {
        super(String.format("Url is needed more parameters. They are marked in {} - %s", text));
    }
}
