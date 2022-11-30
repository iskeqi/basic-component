package tech.taoq.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tech.taoq.common.exception.client.ParamIllegalException;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/**
 * 基于 javax.validation.Validator 封装的校验工具类
 *
 * @author keqi
 */
@Component
public class ValidatorUtil {

    private static Validator validator;

    @Autowired
    public void setValidator(Validator validator) {
        ValidatorUtil.validator = validator;
    }

    /**
     * 校验指定对象(支持嵌套子对象的校验)
     *
     * @param object object
     * @return 满足约束条件返回 true，不满足则返回 false
     */
    public static boolean checkValidate(Object object) {
        if (object == null) {
            return false;
        }
        return CollectionUtils.isEmpty(validator.validate(object));
    }

    /**
     * 校验指定对象(支持嵌套子对象的校验)
     *
     * @param object object
     * @throws ParamIllegalException 如果不满足条件会抛出异常
     */
    public static void validate(Object object) throws ParamIllegalException {
        if (object == null) {
            throw new ParamIllegalException("the validated param is null");
        }
        throw new ConstraintViolationException(validator.validate(object));
    }
}
