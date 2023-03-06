-- Query Application entity
SELECT * FROM
    ZUOEHTIO.TBL_APPLICATION APPL
    LEFT JOIN ZUOEHTIO.TBL_REQUESTOR_INFO REQU ON APPL.APPLICATION_ID = REQU.APPLICATION_ID
    LEFT JOIN ZUOEHTIO.TBL_COMPANY_INFO COMP ON APPL.APPLICATION_ID = COMP.APPLICATION_ID
    LEFT JOIN ZUOEHTIO.TBL_PROJECT PROJ ON APPL.APPLICATION_ID = PROJ.APPLICATION_ID
    LEFT JOIN ZUOEHTIO.TBL_SERVICE SVCE ON PROJ.PROJECT_ID = SVCE.PROJECT_ID
    LEFT JOIN ZUOEHTIO.TBL_INTENTION INTN ON PROJ.PROJECT_ID = INTN.PROJECT_ID
    LEFT JOIN ZUOEHTIO.TBL_PROGRESS PGSS ON PROJ.PROJECT_ID = PGSS.PROJECT_ID
WHERE
    APPL.APPLICATION_ID = :APPLICATION_ID;



-- Original
select
	*
from
    ZUOEHTIO.TBL_PROJECT PROJ
	INNER JOIN ZUOEHTIO.TBL_APPLICATION APPL ON APPL.APPLICATION_ID = PROJ.APPLICATION_ID
    LEFT JOIN ZUOEHTIO.TBL_REQUESTOR_INFO REQU ON APPL.APPLICATION_ID = REQU.APPLICATION_ID
    LEFT JOIN ZUOEHTIO.TBL_COMPANY_INFO COMP ON APPL.APPLICATION_ID = COMP.APPLICATION_ID
    LEFT JOIN ZUOEHTIO.TBL_SERVICE SVCE ON PROJ.PROJECT_ID = SVCE.PROJECT_ID
    LEFT JOIN ZUOEHTIO.TBL_INTENTION INTN ON PROJ.PROJECT_ID = INTN.PROJECT_ID
where
	proj.project_id in (
		select distinct(tp.project_id) from
			tbl_requestor_info tri
			inner join tbl_application ta on tri.application_id = ta.application_id
			inner join tbl_project tp on ta.application_id = tp.application_id
		where
			tri.data_field = 'REQUESTOR_NAME'
			and tri.data_value like :requestorName
	)
	and proj.project_id in (
		select distinct(tp.project_id) from
			tbl_company_info tci
			inner join tbl_application ta on tci.application_id = ta.application_id
			inner join tbl_project tp on ta.application_id = tp.application_id
		where
			tci.data_field = 'COMPANY_NAME'
			and tci.data_value like :companyName
	)
	and proj.project_id in (
		select distinct(tp.project_id) from
			tbl_service ts
			inner join tbl_project tp on ts.project_id = tp.project_id
		where
			ts.service_code in (:services)
	)
	and proj.project_id in (
		select tp2.project_id from
			tbl_project tp2
		where
			tp2.description like :description
	)
	and proj.project_id in (
		select tp3.project_id from
			tbl_project tp3
		where
			tp3.work_with_diff_abled = :canWorkWithDiffAbled
	)
	and proj.project_id in (
		select tp.project_id from
			tbl_company_info tci2
			inner join tbl_application ta on tci2.application_id = ta.application_id
			inner join tbl_project tp on ta.application_id = tp.application_id
		where
			tci2.data_field = 'EXP_WITH_SPEC_NEED'
			and tci2.data_value in (:diffAbledExp)
	);



-- Second Version
select
	*
from
    ZUOEHTIO.TBL_PROJECT PROJ
	INNER JOIN ZUOEHTIO.TBL_APPLICATION APPL ON APPL.APPLICATION_ID = PROJ.APPLICATION_ID
    LEFT JOIN ZUOEHTIO.TBL_REQUESTOR_INFO REQU ON APPL.APPLICATION_ID = REQU.APPLICATION_ID
    LEFT JOIN ZUOEHTIO.TBL_COMPANY_INFO COMP ON APPL.APPLICATION_ID = COMP.APPLICATION_ID
    LEFT JOIN ZUOEHTIO.TBL_SERVICE SVCE ON PROJ.PROJECT_ID = SVCE.PROJECT_ID
    LEFT JOIN ZUOEHTIO.TBL_INTENTION INTN ON PROJ.PROJECT_ID = INTN.PROJECT_ID
    inner join (
    	select
			i_proj.project_id
		from
			tbl_project i_proj
		where
			i_proj.project_id in (
				select distinct(tp.project_id) from
					tbl_requestor_info tri
					inner join tbl_application ta on tri.application_id = ta.application_id
					inner join tbl_project tp on ta.application_id = tp.application_id
				where
					tri.data_field = 'REQUESTOR_NAME'
					and tri.data_value like :requestorName
			)
			and i_proj.project_id in (
				select distinct(tp.project_id) from
					tbl_company_info tci
					inner join tbl_application ta on tci.application_id = ta.application_id
					inner join tbl_project tp on ta.application_id = tp.application_id
				where
					tci.data_field = 'COMPANY_NAME'
					and tci.data_value like :companyName
			)
			and i_proj.project_id in (
				select distinct(tp.project_id) from
					tbl_service ts
					inner join tbl_project tp on ts.project_id = tp.project_id
				where
					ts.service_code in (:services)
			)
			and i_proj.project_id in (
				select tp2.project_id from
					tbl_project tp2
				where
					tp2.description like :description
			)
			and i_proj.project_id in (
				select tp3.project_id from
					tbl_project tp3
				where
					tp3.work_with_diff_abled = :canWorkWithDiffAbled
			)
			and i_proj.project_id in (
				select tp.project_id from
					tbl_company_info tci2
					inner join tbl_application ta on tci2.application_id = ta.application_id
					inner join tbl_project tp on ta.application_id = tp.application_id
				where
					tci2.data_field = 'EXP_WITH_SPEC_NEED'
					and tci2.data_value in (:diffAbledExp)
			)
    ) PROJ_ID_LIST on proj.project_id = PROJ_ID_LIST.project_id;



-- Third Version
select
	proj_id_list.project_id as projectId,
	requestorName.dataValue as requestorName,
	companyName.dataValue as companyName,
	services.dataValue as services,
	description.dataValue as description,
	canWorkWithDiffAbled.dataValue as canWorkWithDiffAbled,
	diffAbledExp.dataValue as diffAbledExp
from (
	select
		i_proj.project_id as project_id
	from
		zuoehtio.tbl_project i_proj
	where
		i_proj.project_id in (
			select distinct(tp.project_id) from
				zuoehtio.tbl_requestor_info tri
				inner join zuoehtio.tbl_application ta on tri.application_id = ta.application_id
				inner join zuoehtio.tbl_project tp on ta.application_id = tp.application_id
			where
				tri.data_field = 'REQUESTOR_NAME'
				and upper(tri.data_value) like upper(:requestorName)
		)
		and i_proj.project_id in (
			select distinct(tp.project_id) from
				zuoehtio.tbl_company_info tci
				inner join zuoehtio.tbl_application ta on tci.application_id = ta.application_id
				inner join zuoehtio.tbl_project tp on ta.application_id = tp.application_id
			where
				tci.data_field = 'COMPANY_NAME'
				and upper(tci.data_value) like upper(:companyName)
		)
		and i_proj.project_id in (
			select distinct(tp.project_id) from
				zuoehtio.tbl_service ts
				inner join zuoehtio.tbl_project tp on ts.project_id = tp.project_id
			where
				ts.service_code in (:services)
		)
		and i_proj.project_id in (
			select tp2.project_id from
				zuoehtio.tbl_project tp2
			where
				upper(tp2.description) like upper(:description)
		)
		and i_proj.project_id in (
			select tp3.project_id from
				zuoehtio.tbl_project tp3
			where
				tp3.work_with_diff_abled = :canWorkWithDiffAbled
		)
		and i_proj.project_id in (
			select tp.project_id from
				zuoehtio.tbl_company_info tci2
				inner join zuoehtio.tbl_application ta on tci2.application_id = ta.application_id
				inner join zuoehtio.tbl_project tp on ta.application_id = tp.application_id
			where
				tci2.data_field = 'EXP_WITH_SPEC_NEED'
				and tci2.data_value in (:diffAbledExp)
		)
	) proj_id_list,
	lateral (
		select requ.data_value as dataValue
		from
			zuoehtio.tbl_requestor_info requ
			inner join zuoehtio.tbl_application ta2 on requ.application_id = ta2.application_id
			inner join zuoehtio.tbl_project tp4 on ta2.application_id = tp4.application_id
		where
			requ.data_field = 'REQUESTOR_NAME'
			and requ.application_id = ta2.application_id
			and tp4.project_id = proj_id_list.project_id
		) as requestorName,
	lateral (
		select comp.data_value as dataValue
		from
			zuoehtio.tbl_company_info comp
			inner join zuoehtio.tbl_application ta3 on comp.application_id = ta3.application_id
			inner join zuoehtio.tbl_project tp5 on ta3.application_id = tp5.application_id
		where
			comp.data_field = 'COMPANY_NAME'
			and comp.application_id = ta3.application_id
			and tp5.project_id = proj_id_list.project_id
		) as companyName,
	lateral (
		select group_concat(svce.service_code order by svce.service_code asc SEPARATOR ',') as dataValue
		from
			zuoehtio.tbl_service svce
		where
			svce.project_id = proj_id_list.project_id
		) as services,
	lateral (
		select tp6.description as dataValue
		from
			zuoehtio.tbl_project tp6
		where
			tp6.project_id = proj_id_list.project_id
	) as description,
	lateral (
		select tp7.work_with_diff_abled as dataValue
		from zuoehtio.tbl_project tp7
			where
		tp7.project_id = proj_id_list.project_id
	) as canWorkWithDiffAbled,
	lateral (
		select group_concat(comp.data_value order by comp.data_value asc SEPARATOR ',') as dataValue
		from
			zuoehtio.tbl_company_info comp
			inner join zuoehtio.tbl_project tp8 on comp.application_id = tp8.application_id
		where
			comp.data_field = 'EXP_WITH_SPEC_NEED'
			and tp8.project_id = proj_id_list.project_id
	) as diffAbledExp;



-- Revised Version
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
        where
            i_proj.project_id in (
                select distinct(tp.project_id) from
                    zuoehtio.tbl_requestor_info tri
                    inner join zuoehtio.tbl_application ta on tri.application_id = ta.application_id
                    inner join zuoehtio.tbl_project tp on ta.application_id = tp.application_id
                where
                    tri.data_field = 'REQUESTOR_NAME'
                    and upper(tri.data_value) like upper(:requestorName)
            )
            and i_proj.project_id in (
                select distinct(tp.project_id) from
                    zuoehtio.tbl_company_info tci
                    inner join zuoehtio.tbl_application ta on tci.application_id = ta.application_id
                    inner join zuoehtio.tbl_project tp on ta.application_id = tp.application_id
                where
                    tci.data_field = 'COMPANY_NAME'
                    and upper(tci.data_value) like upper(:companyName)
            )
            and i_proj.project_id in (
                select distinct(tp.project_id) from
                    zuoehtio.tbl_service ts
                    inner join zuoehtio.tbl_project tp on ts.project_id = tp.project_id
                where
                    ts.service_code in (:services)
            )
            and i_proj.project_id in (
                select tp2.project_id from
                    zuoehtio.tbl_project tp2
                where
                    upper(tp2.description) like upper(:description)
            )
            and i_proj.project_id in (
                select tp3.project_id from
                    zuoehtio.tbl_project tp3
                where
                    tp3.work_with_diff_abled = :canWorkWithDiffAbled
            )
            and i_proj.project_id in (
                select tp.project_id from
                    zuoehtio.tbl_company_info tci2
                    inner join zuoehtio.tbl_application ta on tci2.application_id = ta.application_id
                    inner join zuoehtio.tbl_project tp on ta.application_id = tp.application_id
                where
                    tci2.data_field = 'EXP_WITH_SPEC_NEED'
                    and tci2.data_value in (:diffAbledExp)
            )
            and i_proj.project_id in (
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
    );