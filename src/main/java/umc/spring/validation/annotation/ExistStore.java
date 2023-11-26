package umc.spring.validation.annotation;

import umc.spring.validation.validator.CategoriesExistValidator;
import umc.spring.validation.validator.StoreExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoreExistValidator.class) //이 어노테이션이 붙은 대상을 해당 클래스가 검증
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistStore {
    String message() default "해당하는 가게가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}