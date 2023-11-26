package umc.spring.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.UserConverter;
import umc.spring.domain.User;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import javax.validation.Valid;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/reviews")
//public class ReviewController {
//    @PostMapping("/")
//    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
//        User user = userCommandService.joinUser(request);
//        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
//    }
//}
