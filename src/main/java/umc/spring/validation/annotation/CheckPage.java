package umc.spring.validation.annotation;

import umc.spring.validation.validator.MissionChallengeValidator;
import umc.spring.validation.validator.PageCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageCheckValidator.class) //이 어노테이션이 붙은 대상을 해당 클래스가 검증
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {
    String message() default "유효하지 않은 페이지 입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
