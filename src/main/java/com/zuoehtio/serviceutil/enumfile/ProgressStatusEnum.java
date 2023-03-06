package com.zuoehtio.serviceutil.enumfile;

import java.util.HashMap;
import java.util.Map;

public enum ProgressStatusEnum {
    ONGOING("Ongoing", "ONGOING"),
    COMPLETED("Completed", "COMPLETED"),
    TERMINATED("Terminated", "TERMINATED");


    // STRUCTURE OF EACH ENUM VALUE ANB THEIR RELEVANT FUNCTIONS
    private final String displayVal;
    private final String value;
    
    ProgressStatusEnum(String displayVal, String value) {
        this.displayVal = displayVal;
        this.value = value;
    }


    // CONSTRUCT LIST OF ENUM'S CODE AND THEIR RESPECTIVE FUNCTIONS
    private static final Map<String, String> valueToDisplayValMap = new HashMap<>();

    static {
        for (ProgressStatusEnum progressStatusEnum : ProgressStatusEnum.values()) {
            valueToDisplayValMap.put(progressStatusEnum.value, progressStatusEnum.displayVal);
        }
    }

    public static boolean isProgressStatus(String keyToCheck) {
        return valueToDisplayValMap.containsKey(keyToCheck);
    }

    public static String getDisplayVal(String key) {
        return valueToDisplayValMap.get(key);
    }
}