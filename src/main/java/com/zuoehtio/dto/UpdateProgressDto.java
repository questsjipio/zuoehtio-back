package com.zuoehtio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zuoehtio.dto.fieldslist.UpdateProgressFieldsList;
import com.zuoehtio.dto.projectandcurrentprogress.ProgressDto;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateProgressDto {
    String projectId = "";

    ProgressDto progressDto = new ProgressDto();

    public UpdateProgressDto(String projectId, ProgressDto progressDto) {
        this.projectId = projectId;
        this.progressDto = progressDto;
    }

    public UpdateProgressDto() {
    }

    @JsonProperty(UpdateProgressFieldsList.PROJECT)
    public ProgressDto getProgressDto() {
        return progressDto;
    }

    @JsonProperty(UpdateProgressFieldsList.PROJECT)
    public void setProgressDto(ProgressDto progressDto) {
        this.progressDto = progressDto;
    }
}