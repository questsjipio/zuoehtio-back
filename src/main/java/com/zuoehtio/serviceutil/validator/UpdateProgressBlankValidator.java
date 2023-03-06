package com.zuoehtio.serviceutil.validator;

import com.zuoehtio.dto.UpdateProgressDto;
import com.zuoehtio.dto.fieldslist.UpdateProgressFieldsList;
import com.zuoehtio.util.GeneralHelper;

import java.util.List;

public class UpdateProgressBlankValidator {
    public static void validateFields (UpdateProgressDto updateProgressDto, List<String> errorList) {
        if (!GeneralHelper.isStringNotNullAndNotBlank(updateProgressDto.getProjectId())) {
            errorList.add(UpdateProgressFieldsList.PROJECT_ID);
        }

        if (updateProgressDto.getProgressDto() != null) {
            if (!GeneralHelper.isStringNotNullAndNotBlank(updateProgressDto.getProgressDto().getStatus())) {
                errorList.add(UpdateProgressFieldsList.PROJECT_STATUS);
            }
        } else {
            errorList.add(UpdateProgressFieldsList.PROJECT);
        }
    }

    private UpdateProgressBlankValidator() {
        // Empty
    }
}