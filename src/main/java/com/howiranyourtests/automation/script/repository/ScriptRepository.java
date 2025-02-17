package com.howiranyourtests.automation.script.repository;

import com.howiranyourtests.automation.script.model.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script, Long> {

    @Query("select s from Script s where s.testCase.feature.id = :featureId")
    public List<Script> findScriptsByFeatureId(Long featureId);
}
