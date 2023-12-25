package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import umc.spring.converter.UserConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.service.userService.UserCommandService;
import umc.spring.service.userService.UserQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserRestController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    //MVC에서 요청 파라미터의 검증에 사용되며, 객체의 필드 값이 특정 조건을 만족하는지 검사할 수 있습니다.
    //@Valid 어노테이션을 사용하려면 먼저 검증할 객체의 필드에 제약 조건을 나타내는 어노테이션을 추가해야 합니다. 예를 들어, @NotNull, @Size, @Min, @Max 등의 어노테이션을 사용할 수 있습니다.
    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }

    @GetMapping("/{user-id}/reviews")
    @Operation(summary = "특정 유저의 리뷰 목록 조회 API", description = "특정 유저의 리뷰들의 목록을 조회하는 API, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    //@Parameters는
//    @Parameters({
//            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
//    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistUser @PathVariable("user-id") Long userId, @CheckPage @RequestParam("page") Integer page ){
        Page<Review> reviewList = userQueryService.getReviewList(userId, page-1);
        StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO = StoreConverter.reviewPreViewListDTO(reviewList);
        return ApiResponse.onSuccess(reviewPreViewListDTO);
    }

    @GetMapping("/{user-id}/missions")
    @Operation(summary = "특정 유저의 미션 목록 조회 API", description = "특정 유저의 미션들의 목록을 조회하는 API, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@ExistUser @PathVariable("user-id") Long userId, @CheckPage @RequestParam("page") Integer page ){
        Page<Mission> missionList = userQueryService.getMissionList(userId, page-1);
        MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO = MissionConverter.missionPreViewListDTO(missionList);
        return ApiResponse.onSuccess(missionPreViewListDTO);
    }
}