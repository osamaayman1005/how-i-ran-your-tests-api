package com.howiranyourtests.testCase.repository;

import com.howiranyourtests.testCase.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    List<TestCase> findByFeatureId(Long featureId); // Filter test cases by featureId

}
