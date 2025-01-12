package com.howiranyourtests.execution.controller;

import com.howiranyourtests.execution.service.ExecutionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutionController {
    private final ExecutionService executionService;
    public ExecutionController(ExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping("/api/execute/{scriptId}")
    public String executeScript(@PathVariable Long scriptId) throws Exception {
        return executionService.executeScript(scriptId);
    }
}
