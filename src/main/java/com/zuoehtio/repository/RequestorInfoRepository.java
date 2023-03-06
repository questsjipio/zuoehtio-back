package com.zuoehtio.repository;

import com.zuoehtio.entity.RequestorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestorInfoRepository extends JpaRepository<RequestorInfo, Long> {
}
