package com.zuoehtio.emquery;

import com.zuoehtio.dto.SearchReqDto;
import com.zuoehtio.dto.SearchResDto;
import com.zuoehtio.entity.Project;
import com.zuoehtio.serviceutil.converter.EntityToDto;
import com.zuoehtio.util.Constants;
import com.zuoehtio.util.GeneralHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectQuery {
    @PersistenceContext
    private EntityManager entityManager;

    public List<SearchResDto> searchProject(SearchReqDto searchReqDto) {
        Query query = entityManager.createNativeQuery(searchReqStatementGenerator(searchReqDto), Project.class);

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getRequestorName())) {
            String requestorName = '%' + searchReqDto.getRequestorName() + '%';
            query.setParameter("requestorName", requestorName);
        }

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getCompanyName())) {
            String companyName = '%' + searchReqDto.getCompanyName() + '%';
            query.setParameter("companyName", companyName);
        }

        if (!searchReqDto.getServices().isEmpty()) {
            query.setParameter("services", searchReqDto.getServices());
        }

        if (searchReqDto.getDescription().length() != 0) {
            String description = '%' + searchReqDto.getDescription() + '%';
            query.setParameter("description", description);
        }

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getCanWorkWithDiffAbled())) {
            boolean canWorkWithDiffAbled = GeneralHelper.numberToBooleanConverter(searchReqDto.getCanWorkWithDiffAbled());
            query.setParameter("canWorkWithDiffAbled", canWorkWithDiffAbled);
        }

        if (!searchReqDto.getDiffAbledExp().isEmpty()) {
            query.setParameter("diffAbledExp", searchReqDto.getDiffAbledExp());
        }

        if (!searchReqDto.getStatus().isEmpty()) {
            query.setParameter("status", searchReqDto.getStatus());
        }

        return query.getResultList().stream()
                .map(result -> EntityToDto.convertProjectEntityToSearchResDto((Project)result))
                .toList();
    }

    private static String searchReqStatementGenerator(SearchReqDto searchReqDto) {
        StringBuilder builder = new StringBuilder();

        builder.append("""
            select
                proj.*
            from
                zuoehtio.tbl_project proj
            where
                proj.project_id in (
                    select
                        i_proj.project_id as project_id
                    from
                        zuoehtio.tbl_project i_proj
        """);

        String criteria = searchReqCriteriaGenerator(searchReqDto);

        if (criteria.length() > 0) {
            builder.append(System.getProperty(Constants.LINE_SEPARATOR));
            builder.append("WHERE");
            builder.append(System.getProperty(Constants.LINE_SEPARATOR));
            builder.append(criteria);
        }

        builder.append("""
                )
        """);

        return builder.toString();
    }

    private static String searchReqCriteriaGenerator(SearchReqDto searchReqDto) {
        StringBuilder builder = new StringBuilder();

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getRequestorName())) {
            builder.append("""
                i_proj.project_id in (
                    select distinct(tp.project_id) from
                        zuoehtio.tbl_requestor_info tri
                        inner join zuoehtio.tbl_application ta on tri.application_id = ta.application_id
                        inner join zuoehtio.tbl_project tp on ta.application_id = tp.application_id
                    where
                        tri.data_field = 'REQUESTOR_NAME'
                        and upper(tri.data_value) like upper(:requestorName)
                )
            """);
        }

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getCompanyName())) {
            appendAndClauseIfStringHasValue(builder);
            builder.append("""
                i_proj.project_id in (
                    select distinct(tp.project_id) from
                        zuoehtio.tbl_company_info tci
                        inner join zuoehtio.tbl_application ta on tci.application_id = ta.application_id
                        inner join zuoehtio.tbl_project tp on ta.application_id = tp.application_id
                    where
                        tci.data_field = 'COMPANY_NAME'
                        and upper(tci.data_value) like upper(:companyName)
                )
            """);
        }

        if (!searchReqDto.getServices().isEmpty()) {
            appendAndClauseIfStringHasValue(builder);
            builder.append("""
                i_proj.project_id in (
                    select distinct(tp.project_id) from
                        zuoehtio.tbl_service ts
                        inner join zuoehtio.tbl_project tp on ts.project_id = tp.project_id
                    where
                        ts.service_code in (:services)
                )
            """);
        }

        if (searchReqDto.getDescription().length() != 0) {
            appendAndClauseIfStringHasValue(builder);
            builder.append("""
                i_proj.project_id in (
                    select tp2.project_id from
                        zuoehtio.tbl_project tp2
                    where
                        upper(tp2.description) like upper(:description)
                )
            """);
        }

        if (GeneralHelper.isStringNotNullAndNotBlank(searchReqDto.getCanWorkWithDiffAbled())) {
            appendAndClauseIfStringHasValue(builder);
            builder.append("""
                i_proj.project_id in (
                    select tp3.project_id from
                        zuoehtio.tbl_project tp3
                    where
                        tp3.work_with_diff_abled = :canWorkWithDiffAbled
                )
            """);
        }

        if (!searchReqDto.getDiffAbledExp().isEmpty()) {
            appendAndClauseIfStringHasValue(builder);
            builder.append("""
                i_proj.project_id in (
                    select tp.project_id from
                        zuoehtio.tbl_company_info tci2
                        inner join zuoehtio.tbl_application ta on tci2.application_id = ta.application_id
                        inner join zuoehtio.tbl_project tp on ta.application_id = tp.application_id
                    where
                        tci2.data_field = 'EXP_WITH_SPEC_NEED'
                        and tci2.data_value in (:diffAbledExp)
                )
            """);
        }

        if (!searchReqDto.getStatus().isEmpty()) {
            appendAndClauseIfStringHasValue(builder);
            builder.append("""
                i_proj.project_id in (
                    select tprog.project_id from
                        zuoehtio.tbl_progress tprog
                    where
                        tprog.progress_id in (
                            select max(tprogi.progress_id) from
                                zuoehtio.tbl_progress tprogi
                            group by tprogi.project_id
                        )
                        and UPPER(tprog.status) = UPPER(:status)
                )
            """);
        }

        return builder.toString();
    }

    private static void appendAndClauseIfStringHasValue(StringBuilder builder) {
        if (builder.length() > 0) {
            builder.append(System.getProperty(Constants.LINE_SEPARATOR));
            builder.append("AND");
            builder.append(System.getProperty(Constants.LINE_SEPARATOR));
        }
    }
}