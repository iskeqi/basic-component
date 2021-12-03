package com.keqi.mail.exception;

import com.keqi.common.exception.third.ThirdServiceErrorException;

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
