package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;
import umc.spring.web.dto.UserRequestDTO;
import umc.spring.web.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewConverter {
    public static ReviewResponseDTO.enrollResultDTO toEnrollResultDTO(Review review){
        return ReviewResponseDTO.enrollResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Review toReview(ReviewRequestDTO.enrollDto request){

        return Review.builder()
                .user(request.getUser())
                .store(request.getStore())
                .content(request.getContent())
                .rate(request.getRate())
                .imagePath(request.getImagePath())
                .build();
    }
}
