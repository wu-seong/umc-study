package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.errorStatus.ErrorStatus;
import umc.spring.service.userService.UserQueryService;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.PassDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
//                                                         해당 어노테이션에 대한 로직, 검증대상은 LocalDate
public class DatePassValidator implements ConstraintValidator<PassDate, LocalDate> {
    @Override
    public void initialize(PassDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate values, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        if(now.isAfter(values)){ //이미 지난 시간 검증
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PASSED_DEADLINE_DATE.getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
