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
@Table(name="tbl_service")
@Getter
@Setter
public class Service extends EntitySuffixFields {
    @Id
    @SequenceGenerator(
            name = "seq_service",
            sequenceName = "seq_service",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_service"
    )
    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "service_code", length = 20, nullable = false)
    private String serviceCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id", nullable = false)
    private Project project;
}