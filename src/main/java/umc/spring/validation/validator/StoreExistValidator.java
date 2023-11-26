package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.errorStatus.ErrorStatus;
import umc.spring.domain.Store;
import umc.spring.service.storeService.StoreQueryService;
import umc.spring.service.userService.UserCommandService;
import umc.spring.service.userService.UserQueryService;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistStore;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
@RequiredArgsConstructor
//                                                              해당 어노테이션에 대한 로직, 검증대상은 Long
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {
    private final StoreQueryService storeQueryService;
    @Override
    public void initialize(ExistStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Store store = storeQueryService.findStore(value);
        if(store == null){ //유효성 검사
            context.disableDefaultConstraintViolation();  //기본 메시지 제거
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation(); //커스텀 메시지를 전달
            return false;
        }
        return true;
    }

}
