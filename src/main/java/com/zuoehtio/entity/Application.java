package com.zuoehtio.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tbl_application")
@Getter
@Setter
public class Application extends EntitySuffixFields {
    @Id
    @SequenceGenerator(
            name = "seq_application",
            sequenceName = "seq_application",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_application"
    )
    @Column(name = "application_id", nullable = false)
    private Long applicationId;

    @Column(name = "on_behalf", nullable = false)
    private Boolean onBehalf;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private List<RequestorInfo> requestorInfoEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private List<CompanyInfo> companyInfoEntities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private List<Project> projectEntities;
}