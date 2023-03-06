package com.zuoehtio.serviceutil.enumfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CompanyInfoEnum {
    ON_BEHALF_COMPANY, COMPANY_NAME, COMPANY_REG_NO, EXP_WITH_SPEC_NEED;

    private static final List<CompanyInfoEnum> companyInfoEnums = new ArrayList<>();

    static {
        Collections.addAll(companyInfoEnums, CompanyInfoEnum.values());
    }

    public static List<CompanyInfoEnum> getCompanyInfoEnums() {
        return companyInfoEnums;
    }
}
