package com.keqi.mail.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.keqi.common.exception.client.ParamIllegalException;
import com.keqi.common.pojo.PageDto;
import com.keqi.common.pojo.enums.DisableEnum;
import com.keqi.common.util.JsonUtil;
import com.keqi.mail.domain.db.MailDO;
import com.keqi.mail.mapper.MailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MailService
 *
 * @author keqi
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "sys:mail")
public class MailService {

    @Autowired
    private MailMapper mailMapper;
    @Autowired
    private MailService mailService;

    private static final Map<String, JavaMailSender> JAVA_MAIL_SENDERS = new ConcurrentHashMap<>();

    public MailDO insert(MailDO param) {
        MailDO t = mailMapper.selectOne(Wrappers.query(new MailDO().setIdentifier(param.getIdentifier())));
        if (t != null) {
            throw new ParamIllegalException("A record with identifier " + param.getIdentifier() + " already exists");
        }

        mailMapper.insert(param);
        return param;
    }

    @CacheEvict(key = "#identifier")
    public void deleteByIdentifier(String identifier) {
        mailMapper.delete(Wrappers.query(new MailDO().setIdentifier(identifier)));
    }

    @CacheEvict(key = "#param.identifier")
    public void updateByIdentifier(MailDO param) {
        MailDO t = new MailDO().setIdentifier(param.getIdentifier());

        param.setId(null);
        param.setIdentifier(null);
        mailMapper.update(param, Wrappers.query(t));
    }

    @Cacheable(key = "#identifier")
    public MailDO getByIdentifier(String identifier) {
        return mailMapper.selectOne(Wrappers.query(new MailDO()
                .setIdentifier(identifier)
                .setDisable(DisableEnum.ENABLE.getCode())));
    }

    public PageDto<MailDO> page(Page<MailDO> param) {
        Page<MailDO> page = mailMapper.selectPage(param, Wrappers.query());
        return new PageDto<>(page.getTotal(), page.getRecords());
    }

    public MailDO isConnect(String identifier) {
        JavaMailSenderImpl javaMailSender = this.initJavaMailSender(identifier);

        boolean isConnect = true;
        try {
            javaMailSender.testConnection();
        } catch (MessagingException e) {
            isConnect = false;
            log.error("Mail server is not available " + e.getMessage(), e);
        }

//        mailService.updateByIdentifier(new MailDO()
//                .setConnect(isConnect ? ConnectEnum.CONNECT.getCode() : ConnectEnum.NOT_CONNECT.getCode())
//                .setIdentifier(identifier));

        return mailService.getByIdentifier(identifier);
    }

    public JavaMailSenderImpl initJavaMailSender(String identifier) {
        MailDO mailDO = mailMapper.selectOne(Wrappers.query(new MailDO().setIdentifier(identifier)));
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        MailProperties mailProperties = new MailProperties();
        mailProperties.setHost(mailDO.getHost());
        mailProperties.setPort(mailDO.getPort());
        mailProperties.setUsername(mailDO.getUsername());
        mailProperties.setPassword(mailDO.getPassword());

        javaMailSender.setHost(mailProperties.getHost());
        if (mailProperties.getPort() != null) {
            javaMailSender.setPort(mailProperties.getPort());
        }
        javaMailSender.setUsername(mailProperties.getUsername());
        javaMailSender.setPassword(mailProperties.getPassword());
        javaMailSender.setProtocol(mailProperties.getProtocol());
        javaMailSender.setDefaultEncoding(mailProperties.getDefaultEncoding().name());
        if (StringUtils.hasText(mailDO.getProperties())) {
            Properties properties = new Properties();
            properties.putAll(JsonUtil.readValue(mailDO.getProperties(), HashMap.class));
            javaMailSender.setJavaMailProperties(properties);
        }

        return javaMailSender;
    }

    public JavaMailSenderImpl getOneByIdentifiers(String... identifiers) {
        return null;
    }
}
