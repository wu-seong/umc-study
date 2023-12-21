package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StoreResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnrollResultDTO{
        Long reviewId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewPreViewListDTO {
        List<StoreResponseDTO.ReviewPreviewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFist;
        Boolean isLast;

    }
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewPreviewDTO {
        String reviewer;
        String evaluation; //리뷰 평가 점수(enum)
        String body;
        LocalDate createdAt;
    }
}
