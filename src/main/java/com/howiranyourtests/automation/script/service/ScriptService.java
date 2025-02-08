package com.howiranyourtests.automation.script.service;

import com.howiranyourtests.automation.script.model.Script;
import com.howiranyourtests.automation.script.model.ScriptAction;
import com.howiranyourtests.automation.script.repository.ScriptActionRepository;
import com.howiranyourtests.automation.script.repository.ScriptRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScriptService {

    private final ScriptRepository scriptRepository;
    private final ScriptActionRepository scriptActionRepository;


    public ScriptService(ScriptRepository scriptRepository, ScriptActionRepository scriptActionRepository) {
        this.scriptRepository = scriptRepository;
        this.scriptActionRepository = scriptActionRepository;
    }

    public List<Script> getAllScripts() {
        return scriptRepository.findAll();
    }

    public Optional<Script> getScriptById(Long id) {
        return scriptRepository.findById(id);
    }

    public Script saveScript(Script script) {
        script = scriptRepository.save(script);
        return script;
    }

    public void deleteScript(Long id) {
        scriptRepository.deleteById(id);
    }

    public Optional<Script> updateScript(Long id, Script updatedScript) {
        Optional<Script> existingScript = scriptRepository.findById(id);
        if (existingScript.isPresent()) {
            updatedScript.setId(id);
            return Optional.of(scriptRepository.save(updatedScript));
        }
        return Optional.empty();
    }
}
