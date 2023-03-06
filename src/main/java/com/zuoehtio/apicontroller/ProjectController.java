package com.zuoehtio.apicontroller;

import com.zuoehtio.dto.ProjectAndCurrentProgressDto;
import com.zuoehtio.dto.RequestFormDto;
import com.zuoehtio.dto.SearchReqDto;
import com.zuoehtio.dto.SearchResDto;
import com.zuoehtio.dto.UpdateProgressDto;
import com.zuoehtio.message.HttpSimpleResponseJson;
import com.zuoehtio.message.errorlist.MsgInfoList;
import com.zuoehtio.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {
    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<HttpSimpleResponseJson> addApplication(
        @RequestBody
        RequestFormDto requestFormDto
    ) {
        logger.info("API START: addApplication()");
        projectService.addApplication(requestFormDto);
        logger.info("API END: To send API response for addApplication()");
        return new ResponseEntity<>(new HttpSimpleResponseJson(HttpStatus.OK, MsgInfoList.SUCCESS_001), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(path = "/search")
    public ResponseEntity<List<SearchResDto>> searchProjects(
        @RequestBody
        SearchReqDto searchReqDto
    ) {
        logger.info("API START: searchProjects()");
        List<SearchResDto> projects = projectService.searchProjects(searchReqDto);
        logger.info("API END: To send API response for searchProjects()");
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectAndCurrentProgressDto> getProject(
        @PathVariable("id")
        String projectId
    ) {
        logger.info("API START: getProject()");
        ProjectAndCurrentProgressDto projectAndCurrentProgressDto = projectService.getProjects(projectId);
        logger.info("API END: To send API response for getProject()");
        return new ResponseEntity<>(projectAndCurrentProgressDto, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(path = "/updateProgress")
    public ResponseEntity<HttpSimpleResponseJson> updateProgress(
        @RequestBody
        UpdateProgressDto updateProgressDto
    ) {
        logger.info("API START: updateProgress()");
        projectService.updateProgress(updateProgressDto);
        logger.info("API END: To send API response for updateProgress()");
        return new ResponseEntity<>(new HttpSimpleResponseJson(HttpStatus.OK, MsgInfoList.SUCCESS_001), HttpStatus.OK);
    }
}