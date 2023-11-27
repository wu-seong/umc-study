package umc.spring.service.missionService;

import umc.spring.domain.Mission;
import umc.spring.domain.User;

public interface MissionQueryService {
    public Mission findMission(Long id);

    public boolean isChallenge(User user, Mission mission);
}
