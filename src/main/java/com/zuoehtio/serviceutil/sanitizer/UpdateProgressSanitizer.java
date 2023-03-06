package com.zuoehtio.serviceutil.sanitizer;

import com.zuoehtio.dto.projectandcurrentprogress.ProgressDto;
import com.zuoehtio.dto.UpdateProgressDto;

public class UpdateProgressSanitizer {
    public static UpdateProgressDto getNoWhitespaceDto(com.zuoehtio.dto.UpdateProgressDto updateProgressDto) {
        UpdateProgressDto sanitizedDto = new UpdateProgressDto();
        sanitizedDto.setProjectId(updateProgressDto.getProjectId().trim());
        sanitizedDto.setProgressDto(new ProgressDto(
            updateProgressDto.getProgressDto().getStatus().trim(),
            updateProgressDto.getProgressDto().getBriefTechnicalRequirements().trim()
        ));

        return sanitizedDto;
    }

    private UpdateProgressSanitizer() {
        // Empty
    }
}