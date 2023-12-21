package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {
    public static Review toReview(StoreRequestDTO.ReviewDto request){
        return Review.builder()
              //  .user(request.getUser())
              //  .store(request.getStore())
                .content(request.getContent())
                .rate(request.getRate())
              //  .imagePath(request.getImagePath())
                .build();
    }

    public static StoreResponseDTO.EnrollResultDTO toEnrollResultDTO(Review review){
        return StoreResponseDTO.EnrollResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static StoreResponseDTO.ReviewPreviewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreviewDTO.builder()
                .reviewer(review.getUser().getName())
                .evaluation(review.getRate().toString())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContent())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());
        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFist(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewList.getSize())
                .reviewList(reviewPreviewDTOList)
                .build();
    }
}
