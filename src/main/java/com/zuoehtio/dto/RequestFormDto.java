package com.zuoehtio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zuoehtio.dto.requestform.CompanyDto;
import com.zuoehtio.dto.requestform.ProjectInfoDto;
import com.zuoehtio.dto.requestform.RequestorDto;
import com.zuoehtio.dto.fieldslist.RequestFormFieldsList;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestFormDto {
    private RequestorDto requestorDto = new RequestorDto();
    private CompanyDto companyDto = new CompanyDto();
    private List<ProjectInfoDto> projects = new ArrayList<>();

    public RequestFormDto() {
        // Empty constructor
    }

    public RequestFormDto(RequestorDto requestorDto, CompanyDto companyDto, List<ProjectInfoDto> projects) {
        this.requestorDto = requestorDto;
        this.companyDto = companyDto;
        this.projects = projects;
    }

    @JsonProperty(RequestFormFieldsList.REQUESTOR)
    public RequestorDto getRequestorDto() {
        return requestorDto;
    }

    @JsonProperty(RequestFormFieldsList.REQUESTOR)
    public void setRequestorDto(RequestorDto requestorDto) {
        this.requestorDto = requestorDto;
    }

    @JsonProperty(RequestFormFieldsList.COMPANY)
    public CompanyDto getCompanyDto() {
        return companyDto;
    }

    @JsonProperty(RequestFormFieldsList.COMPANY)
    public void setCompanyDto(CompanyDto companyDto) {
        this.companyDto = companyDto;
    }

    @JsonProperty(RequestFormFieldsList.PROJECTS)
    public List<ProjectInfoDto> getProjects() {
        return projects;
    }

    @JsonProperty(RequestFormFieldsList.PROJECTS)
    public void setProjects(List<ProjectInfoDto> projects) {
        this.projects = projects;
    }
}
