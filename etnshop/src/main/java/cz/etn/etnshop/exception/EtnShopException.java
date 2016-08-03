package cz.etn.etnshop.exception;

public class EtnShopException extends RuntimeException {
    public EtnShopException() {
    }

    public EtnShopException(String message) {
        super(message);
    }

    public EtnShopException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtnShopException(Throwable cause) {
        super(cause);
    }

    public EtnShopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
