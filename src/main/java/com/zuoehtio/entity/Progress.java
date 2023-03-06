package com.zuoehtio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_progress")
@Getter
@Setter
public class Progress extends EntitySuffixFields {
    @Id
    @SequenceGenerator(
            name = "seq_current_progress",
            sequenceName = "seq_current_progress",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_current_progress"
    )
    @Column(name = "progress_id", nullable = false)
    private Long progressId;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "brief_technical_requirements", length = 500)
    private String briefTechnicalRequirements;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id", nullable = false)
    private Project project;
}