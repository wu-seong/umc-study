package umc.spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.domain.Store;
import umc.spring.domain.User;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class addDto{

        private Store store;

        private Integer minimumAmount;

        private Integer rewardPoint;

        private LocalDate deadline;

        public void setStore(Store store){
            this.store = store;
        }
    }
}
