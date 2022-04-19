package tech.taoq.sso.service.impl;

import org.springframework.stereotype.Service;
import tech.taoq.sso.domain.dto.AuthenticateDto;
import tech.taoq.sso.domain.dto.LoginDto;
import tech.taoq.sso.domain.param.AuthenticateParam;
import tech.taoq.sso.domain.param.LoginParam;

/**
 * 认证管理
 *
 * @author keqi
 */
@Service
public class AuthService {

    public LoginDto login(LoginParam param) {
        return null;
    }

    public void logout() {

    }

    public AuthenticateDto authenticateByToken(AuthenticateParam param) {
        return null;
    }
}
