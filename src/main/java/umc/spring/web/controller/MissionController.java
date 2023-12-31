package umc.spring.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.service.missionService.MissionCommandService;
import umc.spring.service.missionService.MissionQueryService;
import umc.spring.service.userService.UserQueryService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.UserMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
public class MissionController {
    private final UserQueryService userQueryService;
    private final MissionQueryService missionQueryService;
    private final MissionCommandService missionCommandService;
    @PostMapping("/{mission-id}")
    public ApiResponse<UserMissionResponseDTO.acceptResultDTO> accept(@PathVariable("mission-id") @ExistMission Long missionId ){
        //원래는 인증정보로 유저를 가져오지만 이 과정은 생략, 아무유저나 가져옴
        User user = userQueryService.findUser(2L);
        //PathVariable 통해 mission 가져오기
        Mission mission = missionQueryService.findMission(missionId);
        //user와 mission을 주고 매핑을 시킴
        MissionRequestDTO.acceptDto acceptDto = new MissionRequestDTO.acceptDto(user, mission);
        UserMission userMission = missionCommandService.acceptMission(acceptDto);

        return ApiResponse.onSuccess(MissionConverter.toAcceptResultDTO(userMission));
    }
}
