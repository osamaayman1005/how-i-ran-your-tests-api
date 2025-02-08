package com.howiranyourtests.automation.script.repository;

import com.howiranyourtests.automation.script.model.Script;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScriptRepository extends JpaRepository<Script, Long> {

}
