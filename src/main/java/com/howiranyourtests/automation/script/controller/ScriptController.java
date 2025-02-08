package com.howiranyourtests.automation.script.controller;

import com.howiranyourtests.automation.script.model.Script;
import com.howiranyourtests.automation.script.service.ScriptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/scripts")
public class ScriptController {

    private final ScriptService scriptService;


    // Constructor injection
    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @GetMapping
    public List<Script> getAllScripts() {
        return scriptService.getAllScripts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Script> getScriptById(@PathVariable Long id) {
        Optional<Script> script = scriptService.getScriptById(id);
        return script.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Script> createScript(@RequestBody Script script) {
        Script savedScript = scriptService.saveScript(script);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScript);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Script> updateScript(@PathVariable Long id, @RequestBody Script updatedScript) {
        Optional<Script> updated = scriptService.updateScript(id, updatedScript);
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> deleteScript(@PathVariable Long id) {
        scriptService.deleteScript(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Script with id:" + id + " was deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
