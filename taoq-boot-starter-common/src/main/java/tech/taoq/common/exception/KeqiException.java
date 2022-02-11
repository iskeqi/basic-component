package tech.taoq.common.exception;

/**
 * KeqiException
 *
 * @author keqi
 */
public abstract class KeqiException extends RuntimeException {

    private static final long serialVersionUID = 3267680643599580436L;

    /**
     * 返回状态码
     */
    private String status;

    public KeqiException(String status, String message) {
        super(message);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
