package umc.spring.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.service.missionService.MissionCommandService;
import umc.spring.service.reviewService.ReviewCommandService;
import umc.spring.service.storeService.StoreQueryService;
import umc.spring.service.userService.UserQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

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
    public ApiResponse<StoreResponseDTO.EnrollResultDTO> enroll( @PathVariable("store-id") @ExistStore Long storeId, @RequestBody @Valid StoreRequestDTO.ReviewDto request){
        //원래는 인증정보로 유저를 가져오지만 이 과정은 생략, 아무유저나 가져옴
        User user = userQueryService.findUser(2L);

        //PathVariable 통해 store가져오기
        Store store = storeQueryService.findStore(storeId);
        //reviewDTO 넘기기
        request.setUserStore(user, store);
        Review review = reviewCommandService.enrollReview(request);
        return ApiResponse.onSuccess(StoreConverter.toEnrollResultDTO(review));
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


    //@Operation은 API에 대한 설명
    //@ApiResponses는 @ApiResponse로 각가의 응답을 담음, 임시로 사용하기에 적합
    @GetMapping("/{store-id}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable("store-id") Long storeId, @CheckPage @RequestParam("page") Integer page ){
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page-1);
        StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO = StoreConverter.reviewPreViewListDTO(reviewList);
        return ApiResponse.onSuccess(reviewPreViewListDTO);
    }
    @GetMapping("/{store-id}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@ExistStore @PathVariable("store-id") Long storeId, @CheckPage @RequestParam("page") Integer page ){
        Page<Mission> missionList = storeQueryService.getMissionList(storeId, page-1);
        MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO = MissionConverter.missionPreViewListDTO(missionList);
        return ApiResponse.onSuccess(missionPreViewListDTO);
    }
}
