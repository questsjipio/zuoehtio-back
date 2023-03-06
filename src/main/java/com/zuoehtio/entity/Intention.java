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
@Table(name="tbl_intention")
@Getter
@Setter
public class Intention extends EntitySuffixFields {
    @Id
    @SequenceGenerator(
            name = "seq_intention",
            sequenceName = "seq_intention",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_intention"
    )
    @Column(name = "intention_id", nullable = false)
    private Long intentionId;

    @Column(name = "intention_code", length = 20, nullable = false)
    private String intentionCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id", nullable = false)
    private Project project;
}