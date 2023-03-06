package com.zuoehtio.serviceutil.converter;

import com.zuoehtio.dto.RequestFormDto;
import com.zuoehtio.dto.requestform.CompanyDto;
import com.zuoehtio.dto.requestform.ProjectInfoDto;
import com.zuoehtio.dto.requestform.RequestorDto;
import com.zuoehtio.entity.CompanyInfo;
import com.zuoehtio.entity.Intention;
import com.zuoehtio.entity.Progress;
import com.zuoehtio.entity.Project;
import com.zuoehtio.entity.RequestorInfo;
import com.zuoehtio.entity.Service;
import com.zuoehtio.entity.Application;
import com.zuoehtio.message.errorlist.MsgInfoList;
import com.zuoehtio.message.AppException;
import com.zuoehtio.serviceutil.enumfile.CompanyInfoEnum;
import com.zuoehtio.serviceutil.enumfile.ProgressStatusEnum;
import com.zuoehtio.util.Constants;
import com.zuoehtio.util.GeneralHelper;
import com.zuoehtio.serviceutil.enumfile.RequestorInfoEnum;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DtoToEntity {
    public static Application requestorFormDtoToApplicationEntity(RequestFormDto requestFormDto) {
        Application application = new Application();
        application.setOnBehalf(GeneralHelper.numberToBooleanConverter(requestFormDto.getCompanyDto().getOnBehalf()));

        application.setRequestorInfoEntities(requestorDtoToRequestorInfoEntities(requestFormDto.getRequestorDto()));
        application.setCompanyInfoEntities(companyDtoToCompanyInfoEntities(requestFormDto.getCompanyDto()));
        application.setProjectEntities(projectInfoDtosToProjectEntities(requestFormDto.getProjects()));

        application.getRequestorInfoEntities().forEach(requestorInfoEntity -> requestorInfoEntity.setApplication(application));
        application.getCompanyInfoEntities().forEach(companyInfoEntity -> companyInfoEntity.setApplication(application));
        application.getProjectEntities().forEach(projectEntity -> projectEntity.setApplication(application));

        return application;
    }

    public static List<RequestorInfo> requestorDtoToRequestorInfoEntities(RequestorDto requestorDto) {
        List<RequestorInfo> requestorInfos = new ArrayList<>();

        RequestorInfoEnum.getRequestorInfoEnums().forEach(enumItem -> {
            RequestorInfo requestorInfo = new RequestorInfo();
            requestorInfo.setDataField(enumItem.toString());

            switch (enumItem) {
                case REQUESTOR_NAME -> requestorInfo.setDataValue(requestorDto.getName());
                case SALUTATION -> requestorInfo.setDataValue(requestorDto.getSalutation());
                case CONTACT_NO -> requestorInfo.setDataValue(requestorDto.getContactNo());
                case EMAIL -> requestorInfo.setDataValue(requestorDto.getEmail());
                default -> throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_008);
            }

            requestorInfos.add(requestorInfo);
        });

        return requestorInfos;
    }

    public static List<CompanyInfo> companyDtoToCompanyInfoEntities(CompanyDto companyDto) {
        List<CompanyInfo> companyInfos = new ArrayList<>();

        if (companyDto.getOnBehalf().equalsIgnoreCase(Constants.COMPANY_ONBEHALF_YES)) {
            List<CompanyInfoEnum> companyInfoEnums1 = new ArrayList<>(Arrays.asList(CompanyInfoEnum.COMPANY_NAME, CompanyInfoEnum.COMPANY_REG_NO));
            companyInfoEnums1.forEach(enumItem -> {
                CompanyInfo companyInfo = new CompanyInfo();
                companyInfo.setDataField(enumItem.toString());

                switch (enumItem) {
                    case COMPANY_NAME -> companyInfo.setDataValue(companyDto.getName());
                    case COMPANY_REG_NO -> companyInfo.setDataValue(companyDto.getRegNo());
                    default -> throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_008);
                }

                companyInfos.add(companyInfo);
            });

            companyDto.getDiffAbledExp().forEach(diffAbled -> {
                CompanyInfo companyInfo = new CompanyInfo();
                companyInfo.setDataField(CompanyInfoEnum.EXP_WITH_SPEC_NEED.toString());
                companyInfo.setDataValue(diffAbled);
                companyInfos.add(companyInfo);
            });
        }

        return companyInfos;
    }

    public static List<Project> projectInfoDtosToProjectEntities(List<ProjectInfoDto> projectInfoDtos) {
        List<Project> projectEntities = new ArrayList<>();
        projectInfoDtos.forEach(projectInfoDto -> projectEntities.add(projectInfoDtoToProjectEntity(projectInfoDto)));
        return projectEntities;
    }

    public static Project projectInfoDtoToProjectEntity(ProjectInfoDto projectInfoDto) {
        List<Service> serviceEntities = new ArrayList<>();
        List<Intention> intentionEntities = new ArrayList<>();

        projectInfoDto.getServices().forEach(serviceStrVal -> {
            Service serviceEntity = new Service();
            serviceEntity.setServiceCode(serviceStrVal);
            serviceEntities.add(serviceEntity);
        });

        projectInfoDto.getIntentions().forEach(intentionStrVal -> {
            Intention intentionEntity = new Intention();
            intentionEntity.setIntentionCode(intentionStrVal);
            intentionEntities.add(intentionEntity);
        });

        Project project = new Project();

        project.setDescription(projectInfoDto.getProductDescription());
        project.setWorkWithDiffAbled(GeneralHelper.numberToBooleanConverter(projectInfoDto.getWorkWithDiffAbled()));
        project.setServiceEntities(serviceEntities);
        project.setIntentionEntities(intentionEntities);
        project.setProgressEntities(List.of(getNewProgressEntity()));

        project.getServiceEntities().forEach(serviceEntity -> serviceEntity.setProject(project));
        project.getIntentionEntities().forEach(intentionEntity -> intentionEntity.setProject(project));
        project.getProgressEntities().forEach(progressEntity -> progressEntity.setProject(project));

        return project;
    }

    public static Progress getNewProgressEntity() {
        Progress progress = new Progress();
        progress.setStatus(ProgressStatusEnum.ONGOING.toString());
        progress.setBriefTechnicalRequirements("");
        return progress;
    }

    private DtoToEntity() {
        // Empty
    }
}
