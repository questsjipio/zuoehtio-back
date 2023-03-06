package com.zuoehtio.exception;

import com.zuoehtio.message.AppException;
import com.zuoehtio.message.HttpSimpleResponseJson;
import com.zuoehtio.message.errorlist.MsgInfoList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

    @ExceptionHandler(value = {AppException.class})
    public ResponseEntity<Object> handleAppException(AppException e) {
        logger.error(e.getErrorCode(), e);
        return new ResponseEntity<>(new HttpSimpleResponseJson(e.getHttpStatus(), e), e.getHttpStatus());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.error(MsgInfoList.ERR_GEN_009.getCode(), e);
        return new ResponseEntity<>(new HttpSimpleResponseJson(HttpStatus.INTERNAL_SERVER_ERROR, MsgInfoList.ERR_GEN_009), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception e) {
        logger.error(MsgInfoList.ERR_GEN_007.getCode(), e);
        return new ResponseEntity<>(new HttpSimpleResponseJson(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error(MsgInfoList.ERR_GEN_006.getCode(), e);
        return new ResponseEntity<>(new HttpSimpleResponseJson(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_006), HttpStatus.BAD_REQUEST);
    }
}