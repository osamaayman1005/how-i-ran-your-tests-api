package com.howiranyourtests.automation.execution.controller;

import com.howiranyourtests.automation.execution.service.ExecutionService;
import com.howiranyourtests.global.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutionController {
    private final ExecutionService executionService;
    public ExecutionController(ExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping("/api/v1/execute/{scriptId}")
    public ResponseEntity<ResponseObject> executeScript(@PathVariable Long scriptId) throws Exception {
        return ResponseEntity.ok(
                new ResponseObject(executionService.executeScript(scriptId)));
    }
}
