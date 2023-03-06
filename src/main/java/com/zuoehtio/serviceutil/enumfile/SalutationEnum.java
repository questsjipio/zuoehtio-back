package com.zuoehtio.serviceutil.enumfile;

import java.util.HashMap;
import java.util.Map;

public enum SalutationEnum {
    // ENUM VALUES
    MR("Mr", "MR"),
    MRS("Mrs", "MRS"),
    MS("Ms", "MS"),
    MDM("Mdm", "MDM"),
    DR("Dr", "DR");


    // STRUCTURE OF EACH ENUM VALUE ANB THEIR RELEVANT FUNCTIONS
    private final String displayVal;
    private final String value;

    SalutationEnum(String displayVal, String value) {
        this.displayVal = displayVal;
        this.value = value;
    }


    // CONSTRUCT LIST OF ENUM'S CODE AND THEIR RESPECTIVE FUNCTIONS
    private static final Map<String, String> valueToDisplayValMap = new HashMap<>();

    static {
        for (SalutationEnum salutationEnum : SalutationEnum.values()) {
            valueToDisplayValMap.put(salutationEnum.value, salutationEnum.displayVal);
        }
    }

    public static boolean isSalutationValue(String keyToCheck) {
        return valueToDisplayValMap.containsKey(keyToCheck);
    }

    public static String getDisplayVal(String key) {
        return valueToDisplayValMap.get(key);
    }
}