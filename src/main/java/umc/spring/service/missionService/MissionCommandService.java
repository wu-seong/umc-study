package umc.spring.service.missionService;

import umc.spring.domain.Mission;
import umc.spring.domain.mapping.UserMission;
import umc.spring.validation.annotation.ChallengeMission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    public Mission addMission(MissionRequestDTO.addDto request);

    public UserMission acceptMission(@ChallengeMission MissionRequestDTO.acceptDto acceptDto);
}
