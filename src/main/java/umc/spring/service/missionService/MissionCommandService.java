package umc.spring.service.missionService;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.ReviewRequestDTO;

public interface MissionCommandService {
    public Mission addMission(MissionRequestDTO.addDto request);
}
