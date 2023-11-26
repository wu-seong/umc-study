package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.validation.annotation.ExistCategories;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class UserRequestDTO {
    @Getter
    public static class JoinDto{
        @NotBlank
        String name;

        @NotNull
        Integer gender;

        @NotNull
        LocalDate birth_date;

        @Size(min = 5, max = 20)
        String address;

        @Size(min = 5, max = 20)
        String specAddress;

        @ExistCategories
        List<Long> preferCategory;

    }
}
