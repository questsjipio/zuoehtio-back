package com.zuoehtio.dto.requestform;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestorDto {
    private String name = "";
    private String salutation = "";
    private String contactNo = "";
    private String email = "";

    public RequestorDto() {
        // Empty constructor
    }

    public RequestorDto(String name, String salutation, String contactNo, String email) {
        this.name = name;
        this.salutation = salutation;
        this.contactNo = contactNo;
        this.email = email;
    }
}
