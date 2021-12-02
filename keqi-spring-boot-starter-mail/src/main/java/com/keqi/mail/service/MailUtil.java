package com.keqi.mail.service;

import com.keqi.mail.domain.param.SimpleMailParam;
import org.springframework.stereotype.Component;

/**
 * MailUtil
 *
 * @author keqi
 */
@Component
public class MailUtil {

    /**
     * send simple text mail
     *
     * @param simpleMailParam simpleMailParam
     * @param identifiers     identifiers
     */
    public void sendSimpleMail(SimpleMailParam simpleMailParam, String... identifiers) {

    }

    public void sendMimeMail() {

    }

    public void sendTemplateMail() {

    }
}
