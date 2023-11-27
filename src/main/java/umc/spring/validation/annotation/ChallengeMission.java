package umc.spring.validation.annotation;

import umc.spring.validation.validator.MissionChallengeValidator;
import umc.spring.validation.validator.StoreExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionChallengeValidator.class) //이 어노테이션이 붙은 대상을 해당 클래스가 검증
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ChallengeMission {
    String message() default "이미 수락한 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
