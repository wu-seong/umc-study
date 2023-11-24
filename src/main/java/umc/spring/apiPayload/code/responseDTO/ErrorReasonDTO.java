package umc.spring.apiPayload.code.responseDTO;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorReasonDTO {
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
