package tech.taoq.iot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.taoq.mail.domain.param.SimpleMailParam;
import tech.taoq.mail.util.MailUtil;
import tech.taoq.task.SpringTaskService;

/**
 * @author keqi
 */
@Slf4j
@RestController
@RequestMapping("/test/mail")
public class MailController {

    @Autowired
    private SpringTaskService springTaskService;

    @GetMapping("/test1")
    public void test1() {
        SimpleMailParam param = new SimpleMailParam();
        MailUtil.sendSimpleMail(param);
    }
}
