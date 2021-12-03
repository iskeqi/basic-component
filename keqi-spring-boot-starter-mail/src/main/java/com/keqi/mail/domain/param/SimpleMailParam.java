package com.keqi.mail.domain.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * SimpleMailParam
 *
 * @author keqi
 */
@Data
@Accessors(chain = true)
public class SimpleMailParam {

    /**
     * 收件人
     */
    private String[] to;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件正文（只支持文本，HTML 也是文本）
     */
    private String text;

    /**
     * 抄送人
     */
    private String[] cc;

    /**
     * 隐秘抄送人
     */
    private String[] bcc;

    /**
     * 邮件发送日期（这个时间可以任意指定，会展示在邮件抬头处，表示邮件的发送时间，不指定时，默认是当前时间）
     */
    private Date sentDate;

    /**
     * 回复人邮箱（收件人点击回复的时候，在他的窗口，看到的回复地址，不再是发件人，而是Reply-to字段的值）
     */
    private String replyTo;
}
