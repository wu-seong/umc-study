package umc.spring.service.missionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.MissionRepository;
import umc.spring.validation.annotation.ChallengeMission;
import umc.spring.web.dto.MissionRequestDTO;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Validated
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService{
    private final MissionRepository missionRepository;
    @Override
    @Transactional
    public Mission addMission(MissionRequestDTO.addDto request) {
        //미션 생성
        Mission mission = MissionConverter.toMission(request);
        //가게 양방향 맵핑
        mission.setStore(request.getStore());
        return missionRepository.save(mission);
    }

    @Override
    @Transactional
    public UserMission acceptMission(MissionRequestDTO.acceptDto acceptDto) {
        UserMission userMission = UserMission.createUserMission(acceptDto.getUser(), acceptDto.getMission());
        return userMission;
    }
}
