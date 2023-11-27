package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.errorStatus.ErrorStatus;
import umc.spring.domain.Mission;
import umc.spring.service.missionService.MissionQueryService;
import umc.spring.validation.annotation.ChallengeMission;
import umc.spring.validation.annotation.PassDate;
import umc.spring.web.dto.MissionAcceptDTO;
import umc.spring.web.dto.MissionRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Map;

@Component
@RequiredArgsConstructor
//                                                         해당 어노테이션에 대한 로직, 검증대상은 Long
public class MissionChallengeValidator implements ConstraintValidator<ChallengeMission, MissionRequestDTO.acceptDto> {

    private final MissionQueryService missionQueryService;
    @Override
    public void initialize(ChallengeMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionRequestDTO.acceptDto acceptDto, ConstraintValidatorContext context) {
        boolean isValid = missionQueryService.isChallenge(acceptDto.getUser(), acceptDto.getMission());
        if( !isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_ACCEPTED_MISSION.toString()).addConstraintViolation();
        }
        return isValid;
    }
}
