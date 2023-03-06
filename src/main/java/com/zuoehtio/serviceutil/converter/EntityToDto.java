package com.zuoehtio.serviceutil.converter;

import com.zuoehtio.dto.ProjectAndCurrentProgressDto;
import com.zuoehtio.dto.RequestFormDto;
import com.zuoehtio.dto.SearchResDto;
import com.zuoehtio.dto.projectandcurrentprogress.ProgressDto;
import com.zuoehtio.dto.requestform.CompanyDto;
import com.zuoehtio.dto.requestform.ProjectInfoDto;
import com.zuoehtio.dto.requestform.RequestorDto;
import com.zuoehtio.entity.CompanyInfo;
import com.zuoehtio.entity.Intention;
import com.zuoehtio.entity.Progress;
import com.zuoehtio.entity.Project;
import com.zuoehtio.entity.RequestorInfo;
import com.zuoehtio.entity.Service;
import com.zuoehtio.message.AppException;
import com.zuoehtio.message.errorlist.MsgInfoList;
import com.zuoehtio.serviceutil.ServiceHelper;
import com.zuoehtio.serviceutil.enumfile.CompanyInfoEnum;
import com.zuoehtio.serviceutil.enumfile.DiffAbledEnum;
import com.zuoehtio.serviceutil.enumfile.ProjectInfoServiceEnum;
import com.zuoehtio.serviceutil.enumfile.RequestorInfoEnum;
import com.zuoehtio.util.Constants;
import com.zuoehtio.util.GeneralHelper;
import org.springframework.http.HttpStatus;
import java.util.List;

public class EntityToDto {
    public static SearchResDto convertProjectEntityToSearchResDto (Project project) {
        if (project == null) { return null; }

        // This value must exist
        String requestorName = project.getApplication().getRequestorInfoEntities().stream()
                                .filter(requestorInfoEntity -> requestorInfoEntity.getDataField().equalsIgnoreCase(RequestorInfoEnum.REQUESTOR_NAME.toString()))
                                .toList().get(0).getDataValue();

        // Optional for these values to exist
        String companyName = "";
        String diffAbledExp = "";
        if (!project.getApplication().getCompanyInfoEntities().isEmpty()) {
            companyName = project.getApplication().getCompanyInfoEntities().stream()
                            .filter(companyInfoEntity -> companyInfoEntity.getDataField().equalsIgnoreCase(CompanyInfoEnum.COMPANY_NAME.toString()))
                            .toList().get(0).getDataValue();

            diffAbledExp = String.join(Constants.STRING_DELIMITER, project.getApplication().getCompanyInfoEntities().stream()
                            .filter(companyInfoEntity -> companyInfoEntity.getDataField().equalsIgnoreCase(CompanyInfoEnum.EXP_WITH_SPEC_NEED.toString()))
                            .map(companyInfoEntity -> DiffAbledEnum.getDisplayVal(companyInfoEntity.getDataValue())).toList());
        }

        // Optional for this value to exist
        String services = "";
        if (!project.getServiceEntities().isEmpty()) {
            services = String.join(Constants.STRING_DELIMITER, project.getServiceEntities().stream()
                        .map(serviceEntity -> ProjectInfoServiceEnum.getDisplayVal(serviceEntity.getServiceCode()))
                        .toList());
        }

        // Optional for this value to exist
        String description = "";
        if (!project.getDescription().isEmpty()) {
            description = project.getDescription();
        }

        return new SearchResDto(
            project.getProjectId(),
            requestorName,
            companyName,
            services,
            description,
            project.getWorkWithDiffAbled(),
            diffAbledExp
        );
    }

    public static ProjectAndCurrentProgressDto currentProjectEntityToProjectInfoDto(Project project) {
        // For projectAndCurrentProgressDto.requestFormDto
        RequestFormDto requestFormDto = new RequestFormDto(
            requestorInfoEntityToRequestorInfoDto(project.getApplication().getRequestorInfoEntities()),
            companyInfoEntityToCompanyDto(project.getApplication().getCompanyInfoEntities()),
            List.of(projectEntityToProjectDto(project))
        );

        // For projectAndCurrentProgressDto.currentProgressDto
        Progress progress = ServiceHelper.getCurrentProgress(project.getProgressEntities());
        ProgressDto progressDto;
        if (progress != null) {
            progressDto = new ProgressDto(
                progress.getStatus(),
                progress.getBriefTechnicalRequirements()
            );
        } else {
            throw new AppException();
        }

        // For projectAndCurrentProgressDto
        return new ProjectAndCurrentProgressDto(requestFormDto, progressDto);
    }

    // For projectAndCurrentProgressDto.requestorDto.requestDto
    public static RequestorDto requestorInfoEntityToRequestorInfoDto(List<RequestorInfo> requestorInfoEntities) {
        if (requestorInfoEntities == null || requestorInfoEntities.isEmpty()) { return null; }

        List<RequestorInfoEnum> requestorInfoEnums = RequestorInfoEnum.getRequestorInfoEnums();
        RequestorDto requestorDto = new RequestorDto();

        requestorInfoEnums.forEach(requestorInfoEnum -> {
            String dataValue = requestorInfoEntities.stream()
                                .filter(requestorInfo -> requestorInfo.getDataField().equalsIgnoreCase(requestorInfoEnum.toString()))
                                .map(RequestorInfo::getDataValue)
                                .toList().get(0);

            switch (requestorInfoEnum) {
                case REQUESTOR_NAME -> requestorDto.setName(dataValue);
                case SALUTATION -> requestorDto.setSalutation(dataValue);
                case CONTACT_NO -> requestorDto.setContactNo(dataValue);
                case EMAIL -> requestorDto.setEmail(dataValue);
                default -> throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_008);
            }
        });

        return requestorDto;
    }

    // For projectAndCurrentProgressDto.requestFormDto.companyDto
    public static CompanyDto companyInfoEntityToCompanyDto(List<CompanyInfo> companyInfoEntities) {
        if (companyInfoEntities == null || companyInfoEntities.isEmpty()) { return null; }

        List<CompanyInfoEnum> companyInfoEnums1 = CompanyInfoEnum.getCompanyInfoEnums();
        companyInfoEnums1.remove(CompanyInfoEnum.ON_BEHALF_COMPANY);
        companyInfoEnums1.remove(CompanyInfoEnum.EXP_WITH_SPEC_NEED);

        CompanyDto companyDto = new CompanyDto();

        companyInfoEnums1.forEach(companyInfoEnum -> {
            String dataValue = companyInfoEntities.stream()
                                .filter(companyInfo -> companyInfo.getDataField().equalsIgnoreCase(companyInfoEnum.toString()))
                                .map(CompanyInfo::getDataValue)
                                .toList().get(0);

            switch (companyInfoEnum) {
                case ON_BEHALF_COMPANY -> companyDto.setOnBehalf(dataValue);
                case COMPANY_NAME -> companyDto.setName(dataValue);
                case COMPANY_REG_NO -> companyDto.setRegNo(dataValue);
                default -> throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_008);
            }
        });

        companyDto.setDiffAbledExp(companyInfoEntities.stream()
            .filter(companyInfo -> companyInfo.getDataField().equalsIgnoreCase(CompanyInfoEnum.EXP_WITH_SPEC_NEED.toString()))
            .map(CompanyInfo::getDataValue)
            .toList());

        return companyDto;
    }

    // For projectAndCurrentProgressDto.requestFormDto.projects (List<ProjectInfoDto>)
    public static ProjectInfoDto projectEntityToProjectDto(Project project) {
        if (project == null) { return null; }

        List<String> services = project.getServiceEntities().stream()
                                    .map(Service::getServiceCode)
                                    .toList();

        List<String> intentions = project.getIntentionEntities().stream()
                                    .map(Intention::getIntentionCode)
                                    .toList();

        return new ProjectInfoDto(
            services,
            intentions,
            project.getDescription(),
            GeneralHelper.booleanToNumberConverter(project.getWorkWithDiffAbled())
        );
    }

    private EntityToDto() {
        // Empty
    }
}