package com.zuoehtio.service;

import com.zuoehtio.dto.ProjectAndCurrentProgressDto;
import com.zuoehtio.dto.UpdateProgressDto;
import com.zuoehtio.dto.projectandcurrentprogress.ProgressDto;
import com.zuoehtio.emquery.ProjectQuery;
import com.zuoehtio.dto.SearchReqDto;
import com.zuoehtio.dto.SearchResDto;
import com.zuoehtio.entity.Progress;
import com.zuoehtio.entity.Project;
import com.zuoehtio.repository.ProjectRepository;
import com.zuoehtio.serviceutil.ServiceHelper;
import com.zuoehtio.serviceutil.converter.DtoToEntity;
import com.zuoehtio.dto.RequestFormDto;
import com.zuoehtio.entity.Application;
import com.zuoehtio.message.errorlist.MsgInfoList;
import com.zuoehtio.message.AppException;
import com.zuoehtio.repository.ApplicationRepository;
import com.zuoehtio.serviceutil.converter.EntityToDto;
import com.zuoehtio.serviceutil.enumfile.ProgressStatusEnum;
import com.zuoehtio.serviceutil.sanitizer.RequestFormSanitizer;
import com.zuoehtio.serviceutil.sanitizer.SearchReqSanitizer;
import com.zuoehtio.serviceutil.sanitizer.UpdateProgressSanitizer;
import com.zuoehtio.serviceutil.validator.RequestFormBizValidator;
import com.zuoehtio.serviceutil.validator.RequestFormBlankValidator;
import com.zuoehtio.serviceutil.validator.SearchReqBizValidator;
import com.zuoehtio.serviceutil.validator.UpdateProgressBlankValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProjectService {
    private static Logger logger = LoggerFactory.getLogger(ProjectService.class);
    private final ApplicationRepository applicationRepository;
    private final ProjectRepository projectRepository;
    private final ProjectQuery projectQuery;

    public ProjectService(ApplicationRepository applicationRepository, ProjectRepository projectRepository, ProjectQuery projectQuery) {
        this.applicationRepository = applicationRepository;
        this.projectRepository = projectRepository;
        this.projectQuery = projectQuery;
    }

    @Transactional
    public void addApplication(RequestFormDto requestFormDto) {
        // Validation: Check for missing (for mandatory fields) or wrong values
        this.applicationValidation(requestFormDto);

        // Sanitize
        RequestFormDto newRequestFormDto = RequestFormSanitizer.getNoWhitespaceDto(requestFormDto);

        try {
            // Convert request DTO to entity
            Application application = DtoToEntity.requestorFormDtoToApplicationEntity(newRequestFormDto);

            // Save entity into database
            applicationRepository.save(application);
        } catch (AppException e) {
          throw e;
        } catch (Exception e) {
            String errorMsg1 = MsgInfoList.ERR_GEN_007.getCode() + ": " + "Problem with DtoToEntity or saving of entity";
            logger.error(errorMsg1, e);
            throw new AppException();
        }
    }

    private void applicationValidation(RequestFormDto requestFormDto) {
        // Check whether mandatory fields are blank
        List<String> blankFields = RequestFormBlankValidator.getBlankFields(requestFormDto);
        if (!blankFields.isEmpty()) {
            String errorMsg1 = MsgInfoList.ERR_GEN_005.getCode() + ": " + MsgInfoList.ERR_GEN_005.getMessage();
            String errorMsg2 = MsgInfoList.ERR_GEN_005.getCode() + ": " + blankFields.toString();
            logger.error(errorMsg1);
            logger.error(errorMsg2);
            throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_005);
        }

        // Sanitize
        RequestFormDto newRequestFormDto = RequestFormSanitizer.getNoWhitespaceDto(requestFormDto);

        // Check whether fields have wrong values
        List<String> wrongFields = RequestFormBizValidator.getWrongFields(newRequestFormDto);
        if (!wrongFields.isEmpty()) {
            String errorMsg1 = MsgInfoList.ERR_GEN_006.getCode() + ": " + MsgInfoList.ERR_GEN_006.getMessage();
            String errorMsg2 = MsgInfoList.ERR_GEN_006.getCode() + ": " + wrongFields.toString();
            logger.error(errorMsg1);
            logger.error(errorMsg2);
            throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_006);
        }
    }

    @Transactional
    public List<SearchResDto> searchProjects(SearchReqDto searchReqDto) {
        try {
            // Validation: Check for missing (for mandatory fields) or wrong values
            this.searchProjectsValidation(searchReqDto);

            // Sanitize
            SearchReqDto newSearchReqDto = SearchReqSanitizer.getNoWhitespaceDto(searchReqDto);

            // Search and get result
            return projectQuery.searchProject(newSearchReqDto);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            String errorMsg1 = MsgInfoList.ERR_GEN_007.getCode();
            logger.error(errorMsg1, e);
            throw new AppException();
        }
    }

    public void searchProjectsValidation(SearchReqDto searchReqDto) {
        // Check whether fields have wrong values
        List<String> wrongFields = SearchReqBizValidator.getWrongFields(searchReqDto);
        if (!wrongFields.isEmpty()) {
            String errorMsg1 = MsgInfoList.ERR_GEN_006.getCode() + ": " + MsgInfoList.ERR_GEN_006.getMessage();
            String errorMsg2 = MsgInfoList.ERR_GEN_006.getCode() + ": " + wrongFields.toString();
            logger.error(errorMsg1);
            logger.error(errorMsg2);
            throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_006);
        }
    }

    @Transactional
    public ProjectAndCurrentProgressDto getProjects(String projectId) {
        try {
            // Get existing Project record
            Optional<Project> project = projectRepository.findById(Long.valueOf(projectId));

            // Check whether Project record exist
            if (project.isPresent()) {
                // Convert Project entity to DTO and return to client
                return EntityToDto.currentProjectEntityToProjectInfoDto(project.get());
            }
            // Throw error if Project record does not exist
            else {
                throw new AppException(HttpStatus.NOT_FOUND, MsgInfoList.ERR_GEN_012);
            }
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            String errorMsg1 = MsgInfoList.ERR_GEN_007.getCode();
            logger.error(errorMsg1, e);
            throw new AppException();
        }
    }

    @Transactional
    public void updateProgress(UpdateProgressDto updateProgressDto) {
        try {
            // Blank Validation
            this.updateProgressBlankValidation(updateProgressDto);

            // Sanitize
            UpdateProgressDto newUpdateProgressDto = UpdateProgressSanitizer.getNoWhitespaceDto(updateProgressDto);

            // Get existing Project record
            Optional<Project> projectOptional = projectRepository.findById(Long.valueOf(newUpdateProgressDto.getProjectId()));

            // Check whether Project record exist
            if (projectOptional.isPresent()) {
                Project projectFromDb = projectOptional.get();

                // Check whether Project record can be updated
                this.updateProgressCheckEligiblityOfUpdate(projectFromDb, newUpdateProgressDto);

                // Create new Progress entity
                Progress newProgressEntity = new Progress();
                newProgressEntity.setStatus(newUpdateProgressDto.getProgressDto().getStatus());
                newProgressEntity.setBriefTechnicalRequirements(newUpdateProgressDto.getProgressDto().getBriefTechnicalRequirements());

                // Update Project record
                projectFromDb.getProgressEntities().add(newProgressEntity);
                projectFromDb.getProgressEntities().forEach(progress -> progress.setProject(projectFromDb));

                // Save Project record
                projectRepository.save(projectFromDb);
            }
            // Throw error if Project record does not exist
            else {
                throw new AppException(HttpStatus.NOT_FOUND, MsgInfoList.ERR_GEN_012);
            }
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            String errorMsg1 = MsgInfoList.ERR_GEN_007.getCode();
            logger.error(errorMsg1, e);
            throw new AppException();
        }
    }

    // Check whether mandatory fields are blank
    private void updateProgressBlankValidation(UpdateProgressDto updateProgressDto) {
        List<String> blankFields = new ArrayList<>();
        UpdateProgressBlankValidator.validateFields(updateProgressDto, blankFields);

        if (!blankFields.isEmpty()) {
            String errorMsg1 = MsgInfoList.ERR_GEN_005.getCode() + ": " + MsgInfoList.ERR_GEN_005.getMessage();
            String errorMsg2 = MsgInfoList.ERR_GEN_005.getCode() + ": " + blankFields.toString();
            logger.error(errorMsg1);
            logger.error(errorMsg2);
            throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_005);
        }
    }

    // Check whether fields have wrong values
    public void updateProgressCheckEligiblityOfUpdate(Project currentProject, UpdateProgressDto updateProgressDto) {
        // Get progress entity from project entity
        Progress currentProgress = ServiceHelper.getCurrentProgress(currentProject.getProgressEntities());

        // Get progressDTO from updateProgressDto
        ProgressDto progressDto = updateProgressDto.getProgressDto();

        // Check whether value submitted from client is correct
        if (!ProgressStatusEnum.isProgressStatus(progressDto.getStatus())) {
            String errorMsg = MsgInfoList.ERR_GEN_006.getCode() + ": " + MsgInfoList.ERR_GEN_006.getMessage();
            logger.error(errorMsg);
            throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_006);
        }

        // Check whether project is ONGOING. If it is not, no update is allowed so throw error
        if (!currentProgress.getStatus().equalsIgnoreCase(ProgressStatusEnum.ONGOING.toString())) {
            String errorMsg = MsgInfoList.ERR_GEN_013.getCode() + ": " + MsgInfoList.ERR_GEN_013.getMessage();
            logger.error(errorMsg);
            throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_013);
        }

        // Check whether any change has been made. If it is not, no update is allowed so throw error
        if (
            currentProgress.getStatus().equalsIgnoreCase(progressDto.getStatus())
            && currentProgress.getBriefTechnicalRequirements().equals(progressDto.getBriefTechnicalRequirements())
        ) {
            String errorMsg = MsgInfoList.ERR_GEN_014.getCode() + ": " + MsgInfoList.ERR_GEN_014.getMessage();
            logger.error(errorMsg);
            throw new AppException(HttpStatus.BAD_REQUEST, MsgInfoList.ERR_GEN_014);
        }
    }
}