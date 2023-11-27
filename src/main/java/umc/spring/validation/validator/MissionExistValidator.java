package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.errorStatus.ErrorStatus;
import umc.spring.domain.Mission;
import umc.spring.service.missionService.MissionQueryService;
import umc.spring.validation.annotation.ExistMission;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@Component
@RequiredArgsConstructor
//                                                                   해당 어노테이션에 대한 로직, 검증대상은 List<Long>
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {
    private final MissionQueryService missionQueryService;
    @Override
    public void initialize(ExistMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Mission mission = missionQueryService.findMission(value);
        if(mission == null){ //하나라도 없는 카테고리면 false
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}