package umc.spring.validation.annotation;

import umc.spring.validation.validator.DatePassValidator;
import umc.spring.validation.validator.StoreExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DatePassValidator.class) //이 어노테이션이 붙은 대상을 해당 클래스가 검증
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PassDate {
    String message() default "요청의 데드라인이 이미 지났습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}