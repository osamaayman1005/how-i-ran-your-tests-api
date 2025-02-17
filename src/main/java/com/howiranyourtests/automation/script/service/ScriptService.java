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


    public ScriptService(ScriptRepository scriptRepository) {
        this.scriptRepository = scriptRepository;
    }

    public List<Script> getAllScripts() {
        return this.scriptRepository.findAll();
    }

    public Optional<Script> getScriptById(Long id) {
        return this.scriptRepository.findById(id);
    }
    public List<Script> getAllScriptsForFeature(Long featureId){
        return this.scriptRepository.findScriptsByFeatureId(featureId);
    }

    public Script saveScript(Script script) {
        script = scriptRepository.save(script);
        return script;
    }

    public void deleteScript(Long id) {
        this.scriptRepository.deleteById(id);
    }

    public Optional<Script> updateScript(Long id, Script updatedScript) {
        Optional<Script> existingScript = this.scriptRepository.findById(id);
        if (existingScript.isPresent()) {
            updatedScript.setId(id);
            return Optional.of(this.scriptRepository.save(updatedScript));
        }
        return Optional.empty();
    }
}
