package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

public class UserRequestDTO {
    @Getter
    public static class JoinDto{
        String name;
        Integer gender;
        LocalDate birth_date;
        String address;
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
    }
}
