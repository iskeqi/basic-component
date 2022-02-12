package tech.taoq.mail.exception;

import tech.taoq.common.exception.third.ThirdServiceErrorException;

/**
 * MailException
 *
 * @author keqi
 */
public class MailException extends ThirdServiceErrorException {

    public MailException(String message) {
        super(message);
    }
}
