package com.zuoehtio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zuoehtio.dto.fieldslist.ProgressAndCurrentProgressFieldsList;
import com.zuoehtio.dto.projectandcurrentprogress.ProgressDto;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectAndCurrentProgressDto {
    private RequestFormDto requestFormDto;
    private ProgressDto progressDto;

    public ProjectAndCurrentProgressDto(RequestFormDto requestFormDto, ProgressDto progressDto) {
        this.requestFormDto = requestFormDto;
        this.progressDto = progressDto;
    }

    @JsonProperty(ProgressAndCurrentProgressFieldsList.REQUEST_FORM)
    public RequestFormDto getRequestFormDto() {
        return requestFormDto;
    }

    @JsonProperty(ProgressAndCurrentProgressFieldsList.REQUEST_FORM)
    public void setRequestFormDto(RequestFormDto requestFormDto) {
        this.requestFormDto = requestFormDto;
    }

    @JsonProperty(ProgressAndCurrentProgressFieldsList.CURRENT_PROGRESS)
    public ProgressDto getProgressDto() {
        return progressDto;
    }

    @JsonProperty(ProgressAndCurrentProgressFieldsList.CURRENT_PROGRESS)
    public void setProgressDto(ProgressDto progressDto) {
        this.progressDto = progressDto;
    }
}