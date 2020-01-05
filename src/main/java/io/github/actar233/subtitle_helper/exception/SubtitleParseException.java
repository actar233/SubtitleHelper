package io.github.actar233.subtitle_helper.exception;

public class SubtitleParseException extends RuntimeException {
    public SubtitleParseException() {
    }

    public SubtitleParseException(String message) {
        super(message);
    }

    public SubtitleParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubtitleParseException(Throwable cause) {
        super(cause);
    }

    public SubtitleParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
