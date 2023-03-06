package com.zuoehtio.serviceutil.enumfile;

import java.util.HashMap;
import java.util.Map;

public enum DiffAbledEnum {
    // ENUM VALUES
    PHYSICAL("Physical Disability", "PHYSICAL"), 
    SENSORY("Sensory Disability", "SENSORY"), 
    INTELLECTUAL("Intellectual Disability", "INTELLECTUAL"), 
    AUTISM("Autism Spectrum", "AUTISM"), 
    OTH_DEV_DELAY("Other Developmental Delay", "OTH_DEV_DELAY");

    
    // STRUCTURE OF EACH ENUM VALUE ANB THEIR RELEVANT FUNCTIONS
    private final String displayVal;
    private final String value;

    DiffAbledEnum(String displayVal, String value) {
        this.displayVal = displayVal;
        this.value = value;
    }


    // CONSTRUCT LIST OF ENUM'S CODE AND THEIR RESPECTIVE FUNCTIONS
    private static final Map<String, String> valueToDisplayValMap = new HashMap<>();

    static {
        for (DiffAbledEnum diffAbledEnum : DiffAbledEnum.values()) {
            valueToDisplayValMap.put(diffAbledEnum.value, diffAbledEnum.displayVal);
        }
    }

    public static boolean isDiffAbled(String keyToCheck) {
        return valueToDisplayValMap.containsKey(keyToCheck);
    }

    public static String getDisplayVal(String key) {
        return valueToDisplayValMap.get(key);
    }
}