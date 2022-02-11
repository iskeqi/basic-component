package tech.taoq.web.validator;

import cn.hutool.extra.spring.SpringUtil;
import tech.taoq.web.validator.annotation.DictValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * DictValidateValidator
 *
 * @author keqi
 */
public class DictValidateValidator implements ConstraintValidator<DictValidate, String> {

    private String typeCode;
    private BaseDictValidate validate;

    @Override
    public void initialize(DictValidate constraintAnnotation) {
        typeCode = constraintAnnotation.value();

        validate = SpringUtil.getBean(BaseDictValidate.class);
        if (validate == null) {
            throw new RuntimeException("未找到 BaseDictValidate 的实现类");
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validate.existItemCode(typeCode, value);
    }
}
