package com.zuoehtio.dto.fieldslist;

public class RequestFormFieldsList {
    public static final String REQUESTOR = "requestor";
    public static final String REQUESTOR_NAME = REQUESTOR + ".name";
    public static final String REQUESTOR_SALUTATION = REQUESTOR + ".salutation";
    public static final String REQUESTOR_CONTACTNO = REQUESTOR + ".contactNo";
    public static final String REQUESTOR_EMAIL = REQUESTOR + ".email";

    public static final String COMPANY = "company";
    public static final String COMPANY_ONBEHALF = COMPANY + ".onBehalf";
    public static final String COMPANY_NAME = COMPANY + ".name";
    public static final String COMPANY_REGNO = COMPANY + ".regNo";
    public static final String COMPANY_DIFFABLEDEXP = COMPANY + ".diffAbledExp";

    public static final String PROJECTS = "projects";
    public static final String PROJECTS_SERVICES = PROJECTS + ".[x].services";
    public static final String PROJECTS_INTENTIONS = PROJECTS + ".[x].intentions";
    public static final String PROJECTS_PRODUCTDESCRIPTION = PROJECTS + ".[x].productDescription";
    public static final String PROJECTS_WORKWITHDIFFABLED = PROJECTS + ".[x].workWithDiffAbled";

    private RequestFormFieldsList() {
        // Empty
    }
}