package com.howiranyourtests.testCase.repository;

import com.howiranyourtests.testCase.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
        List<Feature> findByEpicId(Long epicId); // Filter features by epicId

}
