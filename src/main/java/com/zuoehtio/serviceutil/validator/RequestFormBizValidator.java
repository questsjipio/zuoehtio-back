package com.zuoehtio.serviceutil.validator;

import com.zuoehtio.dto.RequestFormDto;
import com.zuoehtio.dto.requestform.CompanyDto;
import com.zuoehtio.dto.requestform.ProjectInfoDto;
import com.zuoehtio.dto.requestform.RequestorDto;
import com.zuoehtio.dto.fieldslist.RequestFormFieldsList;
import com.zuoehtio.serviceutil.enumfile.DiffAbledEnum;
import com.zuoehtio.serviceutil.enumfile.ProjectInfoIntentionEnum;
import com.zuoehtio.serviceutil.enumfile.ProjectInfoServiceEnum;
import com.zuoehtio.serviceutil.enumfile.SalutationEnum;
import com.zuoehtio.util.Constants;
import com.zuoehtio.util.RegexHelper;
import java.util.ArrayList;
import java.util.List;

public class RequestFormBizValidator {
    public static List<String> getWrongFields(RequestFormDto requestFormDto) {
        List<String> wrongProjectInfoFields = new ArrayList<>();
        List<String> wrongFields = new ArrayList<>();

        int i = 0;
        for (ProjectInfoDto projectInfoDto : requestFormDto.getProjects()) {
            List<String> wrongProjectInfoFieldsInIndividualDto = getWrongProjectFields(projectInfoDto, String.valueOf(i));
            if (!wrongProjectInfoFieldsInIndividualDto.isEmpty()) {
                wrongProjectInfoFields.addAll(wrongProjectInfoFieldsInIndividualDto);
            }
        }

        wrongFields.addAll(getWrongRequestorFields(requestFormDto.getRequestorDto()));
        wrongFields.addAll(getWrongCompanyFields(requestFormDto.getCompanyDto()));
        wrongFields.addAll(wrongProjectInfoFields);

        return wrongFields;
    }

    public static List<String> getWrongRequestorFields(RequestorDto requestorDto) {
        List<String> wrongFields = new ArrayList<>();

        if (requestorDto.getName().length() > 100 || !RegexHelper.validateString(RegexHelper.REGEX_PERSON_NAME, requestorDto.getName())) {
            wrongFields.add(RequestFormFieldsList.REQUESTOR_NAME);
        }

        if (!SalutationEnum.isSalutationValue(requestorDto.getSalutation())) {
            wrongFields.add(RequestFormFieldsList.REQUESTOR_SALUTATION);
        }

        if (!RegexHelper.validateString(RegexHelper.REGEX_PHONE_NO_SG, requestorDto.getContactNo())) {
            wrongFields.add(RequestFormFieldsList.REQUESTOR_CONTACTNO);
        }

        if (!RegexHelper.validateString(RegexHelper.REGEX_EMAIL, requestorDto.getEmail())) {
            wrongFields.add(RequestFormFieldsList.REQUESTOR_EMAIL);
        }

        return wrongFields;
    }

    public static List<String> getWrongCompanyFields(CompanyDto companyDto) {
        List<String> wrongFields = new ArrayList<>();

        if (companyDto.getOnBehalf().equalsIgnoreCase(Constants.COMPANY_ONBEHALF_YES)) {
            if (companyDto.getName().length() > 100) {
                wrongFields.add(RequestFormFieldsList.COMPANY_NAME);
            }

            if (
                !(companyDto.getRegNo().length() == 9 || companyDto.getRegNo().length() == 10)
                || !RegexHelper.validateString(RegexHelper.REGEX_ALPHANUMERIC, companyDto.getRegNo()))
            {
                wrongFields.add(RequestFormFieldsList.COMPANY_REGNO);
            }

            companyDto.getDiffAbledExp().forEach(item -> {
                if (!DiffAbledEnum.isDiffAbled(item)) {
                    wrongFields.add(RequestFormFieldsList.COMPANY_DIFFABLEDEXP);
                }
            });
        }

        return wrongFields;
    }

    public static List<String> getWrongProjectFields(ProjectInfoDto projectInfoDto) {
        return getWrongProjectFields(projectInfoDto, "x");
    }

    public static List<String> getWrongProjectFields(ProjectInfoDto projectInfoDto, String index) {
        List<String> wrongFields = new ArrayList<>();

        if (projectInfoDto.getProductDescription().length() > 500) {
            wrongFields.add("[" + index + "]" + RequestFormFieldsList.PROJECTS_PRODUCTDESCRIPTION);
        }

        projectInfoDto.getServices().forEach(item -> {
            if (!ProjectInfoServiceEnum.isProjectInfoService(item)) {
                wrongFields.add("[" + index + "]" + RequestFormFieldsList.PROJECTS_SERVICES);
            }
        });

        projectInfoDto.getIntentions().forEach(item -> {
            if (!ProjectInfoIntentionEnum.isProjectInfoIntention(item)) {
                wrongFields.add("[" + index + "]" + RequestFormFieldsList.PROJECTS_INTENTIONS);
            }
        });

        return wrongFields;
    }

    private RequestFormBizValidator() {
        // Empty
    }
}
