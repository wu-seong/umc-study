package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResponseDTO.MissionPreviewDTO missionPreViewDTO(Mission mission){
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .deadline(mission.getDeadline())
                .minimum_amount(mission.getMinimumAmount())
                .reward_point(mission.getRewardPoint())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }
    public static MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList){
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionList.stream()
                .map(MissionConverter::missionPreViewDTO).collect(Collectors.toList());
        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFist(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionList.getSize())
                .missionList(missionPreviewDTOList)
                .build();
    }
}
