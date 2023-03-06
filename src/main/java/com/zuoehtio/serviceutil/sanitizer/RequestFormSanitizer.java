package com.zuoehtio.serviceutil.sanitizer;

import com.zuoehtio.dto.RequestFormDto;
import com.zuoehtio.dto.requestform.CompanyDto;
import com.zuoehtio.dto.requestform.ProjectInfoDto;
import com.zuoehtio.dto.requestform.RequestorDto;
import com.zuoehtio.util.Constants;
import com.zuoehtio.util.GeneralHelper;
import java.util.ArrayList;
import java.util.List;

public class RequestFormSanitizer {
    public static RequestFormDto getNoWhitespaceDto(RequestFormDto requestFormDto) {
        RequestFormDto sanitisedDto = new RequestFormDto();

        RequestorDto oldRequestorDto = requestFormDto.getRequestorDto();
        sanitisedDto.setRequestorDto(new RequestorDto(
            oldRequestorDto.getName().trim(),
            oldRequestorDto.getSalutation().trim(),
            oldRequestorDto.getContactNo().trim(),
            oldRequestorDto.getEmail().trim()
        ));

        CompanyDto oldCompanyDto = requestFormDto.getCompanyDto();
        if (oldCompanyDto.getOnBehalf().equalsIgnoreCase(Constants.COMPANY_ONBEHALF_YES)) {
            sanitisedDto.setCompanyDto(new CompanyDto(
                oldCompanyDto.getOnBehalf().trim(),
                oldCompanyDto.getName().trim(),
                oldCompanyDto.getRegNo().trim(),
                GeneralHelper.getTrimmedStringList(oldCompanyDto.getDiffAbledExp())
            ));
        } else {
            sanitisedDto.setCompanyDto(new CompanyDto(oldCompanyDto.getOnBehalf().trim(), null, null, null));
        }

        List<ProjectInfoDto> oldProjectInfoDtos = requestFormDto.getProjects();
        List<ProjectInfoDto> newProjectInfoDtos = new ArrayList<>();
        oldProjectInfoDtos.forEach(dto ->
            newProjectInfoDtos.add(new ProjectInfoDto(
                GeneralHelper.getTrimmedStringList(dto.getServices()),
                GeneralHelper.getTrimmedStringList(dto.getIntentions()),
                dto.getProductDescription().trim(),
                dto.getWorkWithDiffAbled()
            ))
        );
        sanitisedDto.setProjects(newProjectInfoDtos);

        return sanitisedDto;
    }

    private RequestFormSanitizer() {
        // Empty
    }
}
