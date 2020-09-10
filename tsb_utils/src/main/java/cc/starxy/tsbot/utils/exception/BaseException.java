package cc.starxy.tsbot.utils.exception;

/**
 * 用户自定义异常
 *
 * @author DONG Jixing
 */
public abstract class BaseException extends RuntimeException {
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
