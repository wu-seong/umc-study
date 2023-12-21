package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.errorStatus.ErrorStatus;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.PassDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
//                                                         해당 어노테이션에 대한 로직, 검증대상 타입은 LocalDate
public class PageCheckValidator implements ConstraintValidator<CheckPage, Integer> {
    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer values, ConstraintValidatorContext context) {
        if(values <= 0){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus._INVALID_PAGE.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}