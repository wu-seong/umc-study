package umc.spring.apiPayload.code.responseDTO;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/*
    Response객체의 필드에 정보를 전달하기 위한 객체
 */
@Builder
@Getter
public class ReasonDTO {
    private HttpStatus httpStatus;
    private final Boolean isSuccess;
    private final String code;
    private final String message;

}
