package tech.taoq.sso.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.common.exception.client.NoAuthException;
import tech.taoq.common.exception.client.ParamIllegalException;
import tech.taoq.sso.domain.db.AccountDO;
import tech.taoq.sso.domain.dto.AuthenticateDto;
import tech.taoq.sso.domain.dto.LoginDto;
import tech.taoq.sso.domain.param.AuthenticateParam;
import tech.taoq.sso.domain.param.LoginParam;
import tech.taoq.sso.domain.param.UpdatePasswordParam;
import tech.taoq.sso.mapper.AccountMapper;

import java.util.Objects;

/**
 * 认证管理
 *
 * @author keqi
 */
@Service
public class AuthService {

    @Autowired
    private AccountMapper accountMapper;

    public LoginDto login(LoginParam param) {
        AccountDO accountDO = accountMapper.selectOne(Wrappers.query(new AccountDO().setAccount(param.getAccount()).setDisable(false)));
        if (accountDO == null || !Objects.equals(param.getPassword(), accountDO.getPassword())) {
            throw new NoAuthException("用户名或密码不正确");
        }
        StpUtil.login(param.getAccount(), param.getDevice());
        LoginDto result = new LoginDto();
        result.setToken(StpUtil.getTokenValue());
        result.setAccount(param.getAccount());
        result.setName(accountDO.getName());
        return result;
    }

    public void logout() {
        StpUtil.logout();
    }

    public AuthenticateDto authenticateByToken(AuthenticateParam param) {
        String loginId = (String) StpUtil.getLoginIdByToken(param.getToken());
        AuthenticateDto result = new AuthenticateDto();
        if (loginId != null) {
            AccountDO accountDO = accountMapper.selectOne(
                    Wrappers.query(new AccountDO().setAccount(loginId)));
            BeanUtils.copyProperties(accountDO, result);
            result.setLogin(true);
            result.setPassword(null);
        } else {
            result.setLogin(false);
        }
        return result;
    }

    public void updatePassword(UpdatePasswordParam param) {
        String loginId = (String) StpUtil.getLoginId();
        AccountDO accountDO = accountMapper.selectOne(Wrappers.query(new AccountDO().setAccount(loginId)));

        if (Objects.equals(param.getOldPassword(), accountDO.getPassword())) {
            AccountDO t = new AccountDO();
            t.setId(accountDO.getId());
            t.setPassword(param.getNewPassword());
            accountMapper.updateById(t);
        } else {
            throw new ParamIllegalException("密码不正确");
        }
    }
}
