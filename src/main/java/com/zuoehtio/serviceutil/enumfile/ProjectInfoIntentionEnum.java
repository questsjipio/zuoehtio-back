package com.zuoehtio.serviceutil.enumfile;

import java.util.HashMap;
import java.util.Map;

public enum ProjectInfoIntentionEnum {
    // ENUM VALUES
    ART_PRODUCT("Art Product", "ART_PRODUCT"),
    MAKER_PRODUCT("Maker Product", "MAKER_PRODUCT"),
    ENTERPRISE_PRODUCT("Enterprise Product", "ENTERPRISE_PRODUCT");


    // STRUCTURE OF EACH ENUM VALUE ANB THEIR RELEVANT FUNCTIONS
    private final String displayVal;
    private final String value;

    ProjectInfoIntentionEnum(String displayVal, String value) {
        this.displayVal = displayVal;
        this.value = value;
    }


    // CONSTRUCT LIST OF ENUM'S CODE AND THEIR RESPECTIVE FUNCTIONS
    private static final Map<String, String> valueToDisplayValMap = new HashMap<>();

    static {
        for (ProjectInfoIntentionEnum projectInfoIntentionEnum : ProjectInfoIntentionEnum.values()) {
            valueToDisplayValMap.put(projectInfoIntentionEnum.value, projectInfoIntentionEnum.displayVal);
        }
    }

    public static boolean isProjectInfoIntention(String keyToCheck) {
        return valueToDisplayValMap.containsKey(keyToCheck);
    }

    public static String getDisplayVal(String key) {
        return valueToDisplayValMap.get(key);
    }
}