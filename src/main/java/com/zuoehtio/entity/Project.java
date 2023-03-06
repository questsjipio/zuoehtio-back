package com.zuoehtio.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tbl_project")
@Getter
@Setter
public class Project extends EntitySuffixFields {
    @Id
    @SequenceGenerator(
            name = "seq_project",
            sequenceName = "seq_project",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_project"
    )
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "work_with_diff_abled")
    private Boolean workWithDiffAbled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Service> serviceEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Intention> intentionEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Progress> progressEntities;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="application_id", nullable = false)
    private Application application;
}