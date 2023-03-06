package com.zuoehtio.dto.projectandcurrentprogress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgressDto {
    private String status;
    private String briefTechnicalRequirements;

    public ProgressDto(String status, String briefTechnicalRequirements) {
        this.status = status;
        this.briefTechnicalRequirements = briefTechnicalRequirements;
    }

    public ProgressDto() {}
}