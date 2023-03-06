package com.zuoehtio.util;

import com.zuoehtio.message.errorlist.MsgInfoList;
import com.zuoehtio.message.AppException;
import com.zuoehtio.serviceutil.enumfile.DiffAbledEnum;
import com.zuoehtio.serviceutil.enumfile.ProjectInfoIntentionEnum;
import com.zuoehtio.serviceutil.enumfile.ProjectInfoServiceEnum;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralHelper {
    public static boolean isStringNotNullAndNotBlank(String str) {
        return str != null && !str.isBlank();
    }

    public static String getTrimmedStringNullable(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    public static List<String> getTrimmedStringList(List<String> stringList) {
        return stringList.stream()
            .map(GeneralHelper::getTrimmedStringNullable)
            .filter(GeneralHelper::isStringNotNullAndNotBlank)
            .toList();
    }

    public static boolean numberToBooleanConverter(String i) {
        return numberToBooleanConverter(Integer.parseInt(i));
    }

    public static boolean numberToBooleanConverter(int i) {
        return switch (i) {
            case 0 -> false;
            case 1 -> true;
            default -> throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_008);
        };
    }

    public static int booleanToNumberConverter(boolean boolVal) {
        if (boolVal) {
            return 1;
        } else {
            return 0;
        }
    }

    public static String convertProjectInfoServiceValsToDisplayVals(String valString) {
        if (!isStringNotNullAndNotBlank(valString)) { return ""; }

        List<String> valList = Arrays.stream(valString.split(",")).toList();
        List<String> displayValList = new ArrayList<>();

        for (String s : valList) {
            displayValList.add(ProjectInfoServiceEnum.getDisplayVal(s));
        }

        return String.join(Constants.STRING_DELIMITER, displayValList);
    }

    public static String convertProjectInfoIntentionValsToDisplayVals(String valString) {
        if (!isStringNotNullAndNotBlank(valString)) { return ""; }

        List<String> valList = Arrays.stream(valString.split(",")).toList();
        List<String> displayValList = new ArrayList<>();

        for (String s : valList) {
            displayValList.add(ProjectInfoIntentionEnum.getDisplayVal(s));
        }

        return String.join(Constants.STRING_DELIMITER, displayValList);
    }

    public static String convertDiffAbledValsToDisplayVals(String valString) {
        if (!isStringNotNullAndNotBlank(valString)) { return ""; }

        List<String> valList = Arrays.stream(valString.split(",")).toList();
        List<String> displayValList = new ArrayList<>();

        for (String s : valList) {
            displayValList.add(DiffAbledEnum.getDisplayVal(s));
        }

        return String.join(Constants.STRING_DELIMITER, displayValList);
    }

    private GeneralHelper() {
        // Empty
    }
}