package com.howiranyourtests.testCase.repository;

import com.howiranyourtests.testCase.model.Epic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpicRepository extends JpaRepository<Epic, Long> {
}
