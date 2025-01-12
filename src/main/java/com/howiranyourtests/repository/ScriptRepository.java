package com.howiranyourtests.repository;

import com.howiranyourtests.script.model.Script;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScriptRepository extends JpaRepository<Script, Long> {

}
