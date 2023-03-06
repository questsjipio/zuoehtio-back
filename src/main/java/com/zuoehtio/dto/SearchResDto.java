package com.zuoehtio.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SearchResDto {
    private Long projectId;
    private String requestorName;
    private String companyName;
    private String services;
    private String description;
    private Boolean canWorkWithDiffAbled;
    private String diffAbledExp;

    public SearchResDto() {
        // Empty constructor
    }

    public SearchResDto(Long projectId, String requestorName, String companyName, String services, String description, Boolean canWorkWithDiffAbled, String diffAbledExp) {
        this.projectId = projectId;
        this.requestorName = requestorName;
        this.companyName = companyName;
        this.services = services;
        this.description = description;
        this.canWorkWithDiffAbled = canWorkWithDiffAbled;
        this.diffAbledExp = diffAbledExp;
    }
}