package umc.spring.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.service.missionService.MissionCommandService;
import umc.spring.service.reviewService.ReviewCommandService;
import umc.spring.service.storeService.StoreQueryService;
import umc.spring.service.userService.UserQueryService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Validated      //클래스가 아닌 메서드 수준에서 검증하려면 필요
public class StoreController {
    private final UserQueryService userQueryService;
    private final StoreQueryService storeQueryService;
    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;
    @PostMapping("/{store-id}/reviews")
    public ApiResponse<ReviewResponseDTO.enrollResultDTO> enroll( @PathVariable("store-id") @ExistStore Long storeId, @RequestBody @Valid ReviewRequestDTO.enrollDto request){
        //원래는 인증정보로 유저를 가져오지만 이 과정은 생략, 아무유저나 가져옴
        User user = userQueryService.findUser(2L);

        //PathVariable 통해 store가져오기
        Store store = storeQueryService.findStore(storeId);
        //reviewDTO 넘기기
        request.setUserStore(user, store);
        Review review = reviewCommandService.enrollReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toEnrollResultDTO(review));
    }

    @PostMapping("/{store-id}/missions")
    public ApiResponse<MissionResponseDTO.addResultDTO> enroll(@PathVariable("store-id") @ExistStore Long storeId, @RequestBody @Valid MissionRequestDTO.addDto request){
        //PathVariable 통해 store가져오기
        Store store = storeQueryService.findStore(storeId);
        //가게 정보 set
        request.setStore(store);
        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.onSuccess(MissionConverter.toAddResultDTO(mission));
    }
}
