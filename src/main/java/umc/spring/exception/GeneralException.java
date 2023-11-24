package umc.spring.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apiPayload.code.errorStatus.BaseErrorCode;
import umc.spring.apiPayload.code.responseDTO.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
