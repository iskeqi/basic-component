package tech.taoq.web.validator;


import tech.taoq.web.validator.annotation.EnumValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * EnumValidateValidator
 *
 * @author keqi
 */
public class EnumValidateValidator implements ConstraintValidator<EnumValidate, String> {

    private Class<?> clazz;
    private boolean useBaseEnumValidate;

    @Override
    public void initialize(EnumValidate constraintAnnotation) {
        clazz = constraintAnnotation.value();
        useBaseEnumValidate = constraintAnnotation.useBaseEnumValidate();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean useBaseEnumValidate = this.useBaseEnumValidate;
        Class<?> clazz = this.clazz;

        if (useBaseEnumValidate) {
            for (Object constant : clazz.getEnumConstants()) {
                BaseEnumValidate baseEnumValidate = (BaseEnumValidate) constant;
                if (baseEnumValidate.existCode(value)) {
                    return true;
                }
            }
        } else {
            // 使用 Enum 类的 name() 方法进行比较
            for (Object constant : clazz.getEnumConstants()) {
                Enum<?> t = (Enum<?>) constant;
                if (t.name().equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }
}
