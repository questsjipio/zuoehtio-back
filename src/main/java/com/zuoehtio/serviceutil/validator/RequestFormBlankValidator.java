package com.zuoehtio.serviceutil.validator;

import com.zuoehtio.dto.RequestFormDto;
import com.zuoehtio.dto.requestform.CompanyDto;
import com.zuoehtio.dto.requestform.ProjectInfoDto;
import com.zuoehtio.dto.requestform.RequestorDto;
import com.zuoehtio.dto.fieldslist.RequestFormFieldsList;
import com.zuoehtio.util.Constants;
import com.zuoehtio.util.GeneralHelper;
import java.util.ArrayList;
import java.util.List;

public class RequestFormBlankValidator {
    public static List<String> getBlankFields(RequestFormDto requestFormDto) {
        List<String> blankProjectInfoFields = new ArrayList<>();
        List<String> blankFields = new ArrayList<>();

        int i = 0;

        for (ProjectInfoDto projectInfoDto : requestFormDto.getProjects()) {
            List<String> blankProjectInfoFieldsInIndividualDto = getBlankProjectInfoFields(projectInfoDto, String.valueOf(i));
            if (!blankProjectInfoFieldsInIndividualDto.isEmpty()) {
                blankProjectInfoFields.addAll(blankProjectInfoFieldsInIndividualDto);
            }
        }

        blankFields.addAll(getBlankRequestorFields(requestFormDto.getRequestorDto()));
        blankFields.addAll(getBlankCompanyFields(requestFormDto.getCompanyDto()));
        blankFields.addAll(blankProjectInfoFields);

        return blankFields;
    }

    public static List<String> getBlankRequestorFields(RequestorDto requestorDto) {
        List<String> fieldsWithMissingValue = new ArrayList<>();

        if (!GeneralHelper.isStringNotNullAndNotBlank(requestorDto.getName())) {
            fieldsWithMissingValue.add(RequestFormFieldsList.REQUESTOR_NAME);
        }

        if (!GeneralHelper.isStringNotNullAndNotBlank(requestorDto.getSalutation())) {
            fieldsWithMissingValue.add(RequestFormFieldsList.REQUESTOR_SALUTATION);
        }

        if (!GeneralHelper.isStringNotNullAndNotBlank(requestorDto.getContactNo())) {
            fieldsWithMissingValue.add(RequestFormFieldsList.REQUESTOR_CONTACTNO);
        }

        if (!GeneralHelper.isStringNotNullAndNotBlank(requestorDto.getEmail())) {
            fieldsWithMissingValue.add(RequestFormFieldsList.REQUESTOR_EMAIL);
        }

        return fieldsWithMissingValue;
    }

    public static List<String> getBlankCompanyFields(CompanyDto companyDto) {
        List<String> fieldsWithMissingValue = new ArrayList<>();

        if (!GeneralHelper.isStringNotNullAndNotBlank(companyDto.getOnBehalf())) {
            fieldsWithMissingValue.add(RequestFormFieldsList.COMPANY_ONBEHALF);
        }

        if (companyDto.getOnBehalf().equalsIgnoreCase(Constants.COMPANY_ONBEHALF_YES)) {
            if (!GeneralHelper.isStringNotNullAndNotBlank(companyDto.getName())) {
                fieldsWithMissingValue.add(RequestFormFieldsList.COMPANY_NAME);
            }

            if (!GeneralHelper.isStringNotNullAndNotBlank(companyDto.getRegNo())) {
                fieldsWithMissingValue.add(RequestFormFieldsList.COMPANY_REGNO);
            }
        }

        return fieldsWithMissingValue;
    }

    public static List<String> getBlankProjectInfoFields(ProjectInfoDto projectInfoDto) {
        return getBlankProjectInfoFields(projectInfoDto, "x");
    }

    public static List<String> getBlankProjectInfoFields(ProjectInfoDto projectInfoDto, String index) {
        List<String> fieldsWithMissingValue = new ArrayList<>();

        List<String> emptyServiceValues = projectInfoDto.getServices().stream().filter(item -> !GeneralHelper.isStringNotNullAndNotBlank(item)).toList();
        if (projectInfoDto.getServices().isEmpty() || !emptyServiceValues.isEmpty()) { fieldsWithMissingValue.add("[" + index + "]" + RequestFormFieldsList.PROJECTS_SERVICES); }

        List<String> emptyIntentionValues = projectInfoDto.getIntentions().stream().filter(item -> !GeneralHelper.isStringNotNullAndNotBlank(item)).toList();
        if (projectInfoDto.getIntentions().isEmpty() || !emptyIntentionValues.isEmpty()) { fieldsWithMissingValue.add("[" + index + "]" + RequestFormFieldsList.PROJECTS_INTENTIONS); }

        if (!GeneralHelper.isStringNotNullAndNotBlank(projectInfoDto.getProductDescription())) {
            fieldsWithMissingValue.add("[" + index + "]" + RequestFormFieldsList.PROJECTS_PRODUCTDESCRIPTION);
        }

        return fieldsWithMissingValue;
    }

    private RequestFormBlankValidator() {
        // Empty
    }
}
