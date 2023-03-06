package com.zuoehtio.serviceutil.enumfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum RequestorInfoEnum {
    REQUESTOR_NAME, SALUTATION, CONTACT_NO, EMAIL;

    private static final List<RequestorInfoEnum> requestorInfoEnums = new ArrayList<>();

    static {
        Collections.addAll(requestorInfoEnums, RequestorInfoEnum.values());
    }

    public static List<RequestorInfoEnum> getRequestorInfoEnums() {
        return requestorInfoEnums;
    }
}