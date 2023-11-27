package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.*;

import java.time.LocalDateTime;

public class MissionConverter {
    public static UserMissionResponseDTO.acceptResultDTO toAcceptResultDTO(UserMission userMission){
        return UserMissionResponseDTO.acceptResultDTO.builder()
                .userMissionId(userMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.addResultDTO toAddResultDTO(Mission mission){
        return MissionResponseDTO.addResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Mission toMission(MissionRequestDTO.addDto request){

        return Mission.builder()
                .store(request.getStore())
                .minimumAmount(request.getMinimumAmount())
                .rewardPoint(request.getRewardPoint())
                .deadline(request.getDeadline())
                .build();
    }
}
