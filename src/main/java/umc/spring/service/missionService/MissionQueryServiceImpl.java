package umc.spring.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.errorStatus.ErrorStatus;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.exception.handler.MissionHandler;
import umc.spring.exception.handler.StoreHandler;
import umc.spring.repository.MissionRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService{
    private final MissionRepository missionRepository;
    @Override
    public Mission findMission(Long id) {
        Optional<Mission> mission = missionRepository.findById(id);
        return mission.orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

    }

    @Override
    public boolean isChallenge(User user, Mission mission) {
        boolean isChallenge = mission.getUserMissionList().contains(user);
        return isChallenge;
    }
}
