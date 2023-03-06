package com.zuoehtio.message;

import com.zuoehtio.message.errorlist.MsgInfo;
import com.zuoehtio.message.errorlist.MsgInfoList;
import com.zuoehtio.util.Constants;
import org.springframework.http.HttpStatus;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AppException extends RuntimeException {
    private final String timestamp = ZonedDateTime.now(ZoneId.of(Constants.TIMEZONE_SG)).format(Constants.DATEFORMAT_WITHTIMEZONE);
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    public AppException() {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorCode = MsgInfoList.ERR_GEN_007.getCode();
        this.message = MsgInfoList.ERR_GEN_007.getMessage();
    }

    public AppException(HttpStatus httpStatus, MsgInfo msgInfo) {
        this.httpStatus = httpStatus;
        this.errorCode = msgInfo.getCode();
        this.message = msgInfo.getMessage();
    }

    public AppException(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = errorMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
