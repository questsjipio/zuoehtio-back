package com.zuoehtio.dto.requestform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDto {
    private String onBehalf = "";
    private String name = "";
    private String regNo = "";
    private List<String> diffAbledExp = new ArrayList<>();

    public CompanyDto() {
        // Empty constructor
    }

    public CompanyDto(String onBehalf, String name, String regNo, List<String> diffAbledExp) {
        this.onBehalf = onBehalf;
        this.name = name;
        this.regNo = regNo;
        this.diffAbledExp = diffAbledExp;
    }
}
