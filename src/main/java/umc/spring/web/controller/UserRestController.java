package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserConverter;
import umc.spring.domain.User;
import umc.spring.service.userService.UserCommandService;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;

    //MVC에서 요청 파라미터의 검증에 사용되며, 객체의 필드 값이 특정 조건을 만족하는지 검사할 수 있습니다.
    //@Valid 어노테이션을 사용하려면 먼저 검증할 객체의 필드에 제약 조건을 나타내는 어노테이션을 추가해야 합니다. 예를 들어, @NotNull, @Size, @Min, @Max 등의 어노테이션을 사용할 수 있습니다.
    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }
}