create database if not exists zuoehtio;

CREATE TABLE if not exists zuoehtio.seq_application (`next_val` bigint DEFAULT NULL);
CREATE TABLE if not exists zuoehtio.seq_project (`next_val` bigint DEFAULT NULL);
CREATE TABLE if not exists zuoehtio.seq_requestor_info (`next_val` bigint DEFAULT NULL);
CREATE TABLE if not exists zuoehtio.seq_company_info (`next_val` bigint DEFAULT NULL);
CREATE TABLE if not exists zuoehtio.seq_service (`next_val` bigint DEFAULT NULL);
CREATE TABLE if not exists zuoehtio.seq_intention (`next_val` bigint DEFAULT NULL);
CREATE TABLE if not exists zuoehtio.seq_current_progress (`next_val` bigint DEFAULT NULL);

INSERT INTO zuoehtio.seq_application(next_val) VALUES (1);
INSERT INTO zuoehtio.seq_project(next_val) VALUES (1);
INSERT INTO zuoehtio.seq_requestor_info(next_val) VALUES (1);
INSERT INTO zuoehtio.seq_company_info(next_val) VALUES (1);
INSERT INTO zuoehtio.seq_service(next_val) VALUES (1);
INSERT INTO zuoehtio.seq_intention(next_val) VALUES (1);
INSERT INTO zuoehtio.seq_current_progress(next_val) VALUES (1);

CREATE TABLE if not exists zuoehtio.tbl_application (
	application_id bigint unsigned NOT NULL,
	on_behalf bool NOT NULL,
	created_ts timestamp DEFAULT CURRENT_TIMESTAMP NOT null,
	created_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	updated_ts timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	updated_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	soft_deleted bool DEFAULT false NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (application_id)
);

CREATE TABLE if not exists zuoehtio.tbl_project (
	project_id bigint unsigned NOT NULL,
	application_id bigint unsigned NOT NULL,
	description varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
	work_with_diff_abled bool NOT NULL,
	created_ts timestamp DEFAULT CURRENT_TIMESTAMP NOT null,
	created_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	updated_ts timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	updated_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	soft_deleted bool DEFAULT false NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (project_id),
	CONSTRAINT FK_tbl_project FOREIGN KEY (application_id) REFERENCES zuoehtio.tbl_application(application_id)
);

CREATE TABLE if not exists zuoehtio.tbl_requestor_info (
	requestor_info_id bigint unsigned NOT NULL,
	application_id bigint unsigned NOT NULL,
	data_field varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	data_value varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	created_ts timestamp DEFAULT CURRENT_TIMESTAMP NOT null,
	created_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	updated_ts timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	updated_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	soft_deleted bool DEFAULT false NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (requestor_info_id),
	CONSTRAINT FK_tbl_requestor_info FOREIGN KEY (application_id) REFERENCES zuoehtio.tbl_application(application_id)
);

CREATE TABLE if not exists zuoehtio.tbl_company_info (
	company_info_id bigint unsigned NOT NULL,
	application_id bigint unsigned NOT NULL,
	data_field varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	data_value varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	created_ts timestamp DEFAULT CURRENT_TIMESTAMP NOT null,
	created_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	updated_ts timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	updated_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	soft_deleted bool DEFAULT false NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (company_info_id),
	CONSTRAINT FK_tbl_company_info FOREIGN KEY (application_id) REFERENCES zuoehtio.tbl_application(application_id)
);

CREATE TABLE if not exists zuoehtio.tbl_service (
	service_id bigint unsigned NOT NULL,
	project_id bigint unsigned NOT NULL,
	service_code varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	created_ts timestamp DEFAULT CURRENT_TIMESTAMP NOT null,
	created_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	updated_ts timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	updated_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	soft_deleted bool DEFAULT false NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (service_id),
	CONSTRAINT FK_tbl_service FOREIGN KEY (project_id) REFERENCES zuoehtio.tbl_project(project_id)
);

CREATE TABLE if not exists zuoehtio.tbl_intention (
	intention_id bigint unsigned NOT NULL,
	project_id bigint unsigned NOT NULL,
	intention_code varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	created_ts timestamp DEFAULT CURRENT_TIMESTAMP NOT null,
	created_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	updated_ts timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	updated_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	soft_deleted bool DEFAULT false NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (intention_id),
	CONSTRAINT FK_tbl_intention FOREIGN KEY (project_id) REFERENCES zuoehtio.tbl_project(project_id)
);

CREATE TABLE if not exists zuoehtio.tbl_progress (
	progress_id bigint unsigned NOT NULL,
	project_id bigint unsigned NOT NULL,
	status varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
	brief_technical_requirements varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
	created_ts timestamp DEFAULT CURRENT_TIMESTAMP NOT null,
	created_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	updated_ts timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	updated_by varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'SYSTEM',
	soft_deleted bool DEFAULT false NOT NULL,
	CONSTRAINT `PRIMARY` PRIMARY KEY (progress_id),
	CONSTRAINT FK_tbl_progress FOREIGN KEY (project_id) REFERENCES zuoehtio.tbl_project(project_id)
);