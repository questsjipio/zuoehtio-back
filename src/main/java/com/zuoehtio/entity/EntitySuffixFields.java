package com.zuoehtio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class EntitySuffixFields {
    @Column(name = "created_by", nullable = false, length=20)
    private String createdBy = "SYSTEM";

    @Column(name = "created_ts", nullable = false)
    private LocalDateTime createdTs = LocalDateTime.now();

    @Column(name = "updated_by", nullable = false, length=20)
    private String updatedBy = "SYSTEM";

    @Column(name = "updated_ts", nullable = false)
    private LocalDateTime updatedTs = LocalDateTime.now();

    @Column(name = "soft_deleted", nullable = false)
    private Boolean softDeleted = false;
}