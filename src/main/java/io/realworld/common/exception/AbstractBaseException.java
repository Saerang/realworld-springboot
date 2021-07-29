package io.realworld.common.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractBaseException extends RuntimeException {

    private static final long serialVersionUID = 8915250994192685393L;

    public abstract HttpStatus getHttpStatus();

    public abstract ErrorCode getErrorCode();

    public AbstractBaseException() {
        super();
    }

    public AbstractBaseException(String msg) {
        super(msg);
    }

    public AbstractBaseException(Throwable e) {
        super(e);
    }

    public AbstractBaseException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

}
