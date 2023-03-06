package com.zuoehtio.dto.requestform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectInfoDto {
    private List<String> services = new ArrayList<>();
    private List<String> intentions = new ArrayList<>();
    private String productDescription = "";
    private int workWithDiffAbled = 0;

    public ProjectInfoDto() {
        // Empty constructor
    }

    public ProjectInfoDto(List<String> services, List<String> intentions, String productDescription, int workWithDiffAbled) {
        this.services = services;
        this.intentions = intentions;
        this.productDescription = productDescription;
        this.workWithDiffAbled = workWithDiffAbled;
    }
}
