package com.zuoehtio.serviceutil.validator;

import com.zuoehtio.serviceutil.enumfile.DiffAbledEnum;
import com.zuoehtio.serviceutil.enumfile.ProjectInfoServiceEnum;
import com.zuoehtio.util.RegexHelper;

import java.util.List;

public class SharedValidator {
    public static void requestorNameValidator(String requestorName, List<String> wrongFields, String fieldName) {
        if (requestorName.length() > 100 || !RegexHelper.validateString(RegexHelper.REGEX_PERSON_NAME, requestorName)) {
            wrongFields.add(fieldName);
        }
    }

    public static void companyNameValidator(String companyName, List<String> wrongFields, String fieldName) {
        if (companyName.length() > 100) {
            wrongFields.add(fieldName);
        }
    }

    public static void servicesValidator(List<String> services, List<String> wrongFields, String fieldName) {
        servicesValidator(services, wrongFields, null, fieldName);
    }

    public static void servicesValidator(List<String> services, List<String> wrongFields, String index, String fieldName) {
        services.forEach(item -> {
            if (!ProjectInfoServiceEnum.isProjectInfoService(item)) {
                if (index != null) {
                    wrongFields.add("[" + index + "]" + fieldName);
                } else {
                    wrongFields.add(fieldName);
                }
            }
        });
    }

    public static void productDescriptionValidator(String productDescription, List<String> wrongFields, String fieldName) {
        productDescriptionValidator(productDescription, wrongFields, null, fieldName);
    }

    public static void productDescriptionValidator(String productDescription, List<String> wrongFields, String index, String fieldName) {
        if (productDescription.length() > 500) {
            if (index != null) {
                wrongFields.add("[" + index + "]" + fieldName);
            } else {
                wrongFields.add(fieldName);
            }
        }
    }

    public static void diffAbledExpValidator(List<String> diffAbledExps, List<String> wrongFields, String fieldName) {
        diffAbledExps.forEach(item -> {
            if (!DiffAbledEnum.isDiffAbled(item)) {
                wrongFields.add(fieldName);
            }
        });
    }

    private SharedValidator() {
        // Empty
    }
}