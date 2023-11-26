package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.validation.annotation.PassDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class addDto{

        private Store store;

        @Positive
        private Integer minimumAmount;

        @Positive
        private Integer rewardPoint;

        @PassDate //근데 찾아보니 날짜 값을 검증하는 @Future과 @Past가 있음..
        private LocalDate deadline;

        public void setStore(Store store){
            this.store = store;
        }
    }
}
