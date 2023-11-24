package umc.spring.apiPayload.code.sucessStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.responseDTO.ReasonDTO;


/*
    성공하는 코드정보를 담고, 코드에 대한 DTO객체를 build하여 생성하는 역할
    1. HTTP코드를 포함하여 생성하는 메소드 2. 포함하지 않고 생성하는 메소드, 두가지가 있음
 */

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    _OK(HttpStatus.OK, "COMMON200", "성공"); //enum 타입도 생성자?를 지정할 수 있음

    // 멤버 관련 응답

    // ~~~ 관련 응답 ....
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();

    }
    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();

    }
}
