package umc.spring.web.dto;


import lombok.Getter;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.enums.Rate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewRequestDTO {

    @Getter
    public static class enrollDto{

        private User user;

        private Store store;

        @NotNull
        private Rate rate;

        @NotNull
        @Size(min = 5)
        private String content;

        @Size(max = 100)
        private String imagePath;
        public void setUserStore(User user, Store store){
            this.user = user;
            this.store = store;
        }
    }
}
