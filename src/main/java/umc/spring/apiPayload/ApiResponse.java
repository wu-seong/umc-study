package umc.spring.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apiPayload.code.errorStatus.BaseErrorCode;
import umc.spring.apiPayload.code.sucessStatus.BaseCode;
import umc.spring.apiPayload.code.sucessStatus.SuccessStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"}) //순서대로 JSON을 반환할 수 있도록 JSON은 순서가 중요하진 않지만 가독성 측면에서 유리
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL) //Result에 대한 내용이 없으면 제외하고 반환을 한다.
    private T result;


    // 성공한 경우 응답 생성

    public static <T> ApiResponse<T> onSuccess(T result){ //성공의 경우는 나눌 필요가 없음, OK 하나면 충분
        return new ApiResponse<>(true, SuccessStatus._OK.getCode() , SuccessStatus._OK.getMessage(), result);
    }
    public static <T> ApiResponse<T> onSuccess(T result, SuccessStatus successStatus){ //혹시 모르니 OK 이외의 성공 상태를 전달하는 메서드
        return new ApiResponse<>(true, successStatus.getCode() , successStatus.getMessage(), result);
    }

    //enum 코드를 받고 DTO를 통해 response객체를 만들어 응답을 반환
    public static <T> ApiResponse<T> of(BaseErrorCode code, T result){  //실패의 경우는 다양하기 때문에 나누어야함 따라서 code 따라서 다름
            return new ApiResponse<>(false, code.getReasonHttpStatus().getCode() , code.getReasonHttpStatus().getMessage(), result);
    }

    // 실패한 경우 응답 생성
    // DTO를 통하지 않고 직접 응답 객체를 만드는 메서드
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(false, code, message, data);
    }
}