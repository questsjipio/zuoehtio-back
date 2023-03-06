package com.zuoehtio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SearchReqDto {
    private String requestorName = "";
    private String companyName = "";
    private List<String> services = new ArrayList<>();
    private String description = "";
    private String canWorkWithDiffAbled = "";
    private List<String> diffAbledExp = new ArrayList<>();
    private String status = "";

    public SearchReqDto() {
        // Empty Constructor
    }

    public SearchReqDto(String requestorName, String companyName, List<String> services, String description, String canWorkWithDiffAbled, List<String> diffAbledExp, String status) {
        this.requestorName = requestorName;
        this.companyName = companyName;
        this.services = services;
        this.description = description;
        this.canWorkWithDiffAbled = canWorkWithDiffAbled;
        this.diffAbledExp = diffAbledExp;
        this.status = status;
    }
}