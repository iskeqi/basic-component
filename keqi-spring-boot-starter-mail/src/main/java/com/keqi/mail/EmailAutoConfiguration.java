package com.keqi.mail;

import org.springframework.context.annotation.ComponentScan;

/**
 * EmailAutoConfiguration
 *
 * @author keqi
 */
@ComponentScan("com.keqi.mail")
public class EmailAutoConfiguration {
    // 邮件服务信息存储到数据库中，并使用 Spring Cache 做缓存

    // 提供发送普通文本的、静态HTML、thymeleaf模板的方法

    // 动态在内存中创建 JavaMailSenderImpl 对象，并维护起来

    // 并使用 test 方法验证信息是否填写正确
}
