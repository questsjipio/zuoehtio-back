package com.zuoehtio.message;

import com.zuoehtio.message.errorlist.MsgInfo;
import com.zuoehtio.message.errorlist.MsgInfoList;
import com.zuoehtio.util.Constants;
import org.springframework.http.HttpStatus;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class HttpSimpleResponseJson {
    private final String timestamp = ZonedDateTime.now(ZoneId.of(Constants.TIMEZONE_SG)).format(Constants.DATEFORMAT_WITHTIMEZONE);
    private int httpStatusCode;
    private String appStatusCode;
    private String message;

    public HttpSimpleResponseJson() {
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.appStatusCode = MsgInfoList.ERR_GEN_007.getCode();
        this.message = MsgInfoList.ERR_GEN_007.getMessage();
    }

    public HttpSimpleResponseJson(HttpStatus httpStatus, AppException appException) {
        this.httpStatusCode = httpStatus.value();
        this.appStatusCode = appException.getErrorCode();
        this.message = appException.getMessage();
    }

    public HttpSimpleResponseJson(HttpStatus httpStatus, MsgInfo msgInfo) {
        this.httpStatusCode = httpStatus.value();
        this.appStatusCode = msgInfo.getCode();
        this.message = msgInfo.getMessage();
    }

    public HttpSimpleResponseJson(HttpStatus httpStatus, String appStatusCode, String errorMessage) {
        this.httpStatusCode = httpStatus.value();
        this.appStatusCode = appStatusCode;
        this.message = errorMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getAppStatusCode() {
        return appStatusCode;
    }

    public String getMessage() {
        return message;
    }
}