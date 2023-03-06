package com.zuoehtio.repository;

import com.zuoehtio.entity.Intention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentionRepository extends JpaRepository<Intention, Long> {
}