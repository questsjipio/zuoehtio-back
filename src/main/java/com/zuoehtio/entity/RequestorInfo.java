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
@Table(name="tbl_requestor_info")
@Getter
@Setter
public class RequestorInfo extends EntitySuffixFields {
    @Id
    @SequenceGenerator(
            name = "seq_requestor_info",
            sequenceName = "seq_requestor_info",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_requestor_info"
    )
    @Column(name = "requestor_info_id", nullable = false)
    private Long requestorInfoId;

    @Column(name = "data_field", length = 20, nullable = false)
    private String dataField;

    @Column(name = "data_value", length = 20, nullable = false)
    private String dataValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="application_id", nullable = false)
    private Application application;
}