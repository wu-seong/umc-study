package umc.spring.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.repository.MissionRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService{
    private final MissionRepository missionRepository;
    @Override
    public Mission findMission(Long id) {
        Optional<Mission> mission = missionRepository.findById(id);
        if(mission.isEmpty()){
            return null;
        }
        return mission.get();
    }

    @Override
    public boolean isChallenge(User user, Mission mission) {
        boolean isChallenge = mission.getUserMissionList().contains(user);
        return isChallenge;
    }
}
