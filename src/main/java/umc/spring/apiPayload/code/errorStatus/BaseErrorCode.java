package umc.spring.apiPayload.code.errorStatus;

import umc.spring.apiPayload.code.responseDTO.ErrorReasonDTO;



public interface BaseErrorCode {

    public ErrorReasonDTO getReason();

    public ErrorReasonDTO getReasonHttpStatus();
}
