package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

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
