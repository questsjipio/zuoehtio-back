package com.zuoehtio.serviceutil.enumfile;

import java.util.HashMap;
import java.util.Map;

public enum ProjectInfoServiceEnum {
    // ENUM VALUES
    THREE_D_PRINTING("3D Printing", "3D_PRINTING"),
    BASIC_ELECTRONIC("Basic Electronic Design and Assembly","BASIC_ELECTRONIC"),
    WEB_DEV("Web Development","WEB_DEV"),
    ARDUINO_PROG("Arduino Programming", "ARDUINO_PROG"),
    RASPBERRY_PI_PROG("Raspberry Pi Programming", "RASPBERRY_PI_PROG");


    // STRUCTURE OF EACH ENUM VALUE ANB THEIR RELEVANT FUNCTIONS
    private final String displayVal;
    private final String value;

    ProjectInfoServiceEnum(String displayVal, String value) {
        this.displayVal = displayVal;
        this.value = value;
    }


    // CONSTRUCT LIST OF ENUM'S CODE AND THEIR RESPECTIVE FUNCTIONS
    private static final Map<String, String> valueToDisplayValMap = new HashMap<>();

    static {
        for (ProjectInfoServiceEnum projectInfoServiceEnum : ProjectInfoServiceEnum.values()) {
            valueToDisplayValMap.put(projectInfoServiceEnum.value, projectInfoServiceEnum.displayVal);
        }
    }

    public static boolean isProjectInfoService(String keyToCheck) {
        return valueToDisplayValMap.containsKey(keyToCheck);
    }

    public static String getDisplayVal(String key) {
        return valueToDisplayValMap.get(key);
    }
}