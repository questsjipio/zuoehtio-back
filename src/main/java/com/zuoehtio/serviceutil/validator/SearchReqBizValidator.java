package com.zuoehtio.serviceutil.validator;

import com.zuoehtio.dto.SearchReqDto;
import com.zuoehtio.dto.fieldslist.SearchReqFieldsList;
import com.zuoehtio.message.AppException;
import com.zuoehtio.serviceutil.enumfile.ProgressStatusEnum;
import com.zuoehtio.util.GeneralHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchReqBizValidator {
    public static List<String> getWrongFields(SearchReqDto searchReqDto) {
        List<String> wrongFields = new ArrayList<>();

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getRequestorName())) {
            SharedValidator.requestorNameValidator(searchReqDto.getRequestorName(), wrongFields, SearchReqFieldsList.REQUESTOR_NAME);
        }

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getCompanyName())) {
            SharedValidator.companyNameValidator(searchReqDto.getCompanyName(), wrongFields, SearchReqFieldsList.COMPANY_NAME);
        }

        if (!searchReqDto.getServices().isEmpty()) {
            SharedValidator.servicesValidator(searchReqDto.getServices(), wrongFields, SearchReqFieldsList.SERVICES);
        }

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getDescription())) {
            SharedValidator.productDescriptionValidator(searchReqDto.getDescription(), wrongFields, SearchReqFieldsList.DESCRIPTION);
        }

        if (!searchReqDto.getDiffAbledExp().isEmpty()) {
            SharedValidator.diffAbledExpValidator(searchReqDto.getDiffAbledExp(), wrongFields, SearchReqFieldsList.DIFFABLEDEXP);
        }

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getStatus()) && !ProgressStatusEnum.isProgressStatus(searchReqDto.getStatus())) {
            wrongFields.add(SearchReqFieldsList.STATUS);
        }

        try {
            if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getCanWorkWithDiffAbled())) {
                GeneralHelper.numberToBooleanConverter(searchReqDto.getCanWorkWithDiffAbled());
            }
        } catch (AppException e) {
            wrongFields.add(SearchReqFieldsList.CANWORKWITHDIFFABLED);
        }

        return wrongFields;
    }

    private SearchReqBizValidator() {
        // Empty
    }
}