package java.ru.spbstu.search;

import static java.lang.String.format;

public class SearchException extends Exception {

    public SearchException(Exception exception) {
        super(exception.getMessage(), exception);
    }

    public SearchException(String message, Object... args) {
        super(format(message, args));
    }
}
