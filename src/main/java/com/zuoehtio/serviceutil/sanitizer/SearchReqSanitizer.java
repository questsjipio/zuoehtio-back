package com.zuoehtio.serviceutil.sanitizer;

import com.zuoehtio.dto.SearchReqDto;
import com.zuoehtio.util.GeneralHelper;

public class SearchReqSanitizer {
    public static SearchReqDto getNoWhitespaceDto(SearchReqDto searchReqDto) {
        SearchReqDto sanitizedDto = new SearchReqDto();
        sanitizedDto.setRequestorName(searchReqDto.getRequestorName().trim());
        sanitizedDto.setCompanyName(searchReqDto.getCompanyName().trim());
        sanitizedDto.setServices(GeneralHelper.getTrimmedStringList(searchReqDto.getServices()));
        sanitizedDto.setDescription(searchReqDto.getDescription().trim());
        sanitizedDto.setCanWorkWithDiffAbled(searchReqDto.getCanWorkWithDiffAbled().trim());
        sanitizedDto.setDiffAbledExp(GeneralHelper.getTrimmedStringList(searchReqDto.getDiffAbledExp()));
        sanitizedDto.setStatus(searchReqDto.getStatus().trim());

        return sanitizedDto;
    }

    private SearchReqSanitizer() {
        // Empty
    }
}