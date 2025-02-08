package com.howiranyourtests.testCase.controller;

import com.howiranyourtests.testCase.dto.TestCaseDto;
import com.howiranyourtests.testCase.model.TestCase;
import com.howiranyourtests.testCase.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test-cases")
public class TestCaseController {

    private final TestCaseService testCaseService;


    // Get all TestCases (optionally filtered by featureId)
    @GetMapping
    public ResponseEntity<List<TestCaseDto>> getAllTestCases(@RequestParam(required = false) Long featureId) {
        List<TestCaseDto> testCases = testCaseService.getAllTestCases(featureId);
        return new ResponseEntity<>(testCases, HttpStatus.OK);
    }

    // Get TestCase by ID
    @GetMapping("/{id}")
    public ResponseEntity<TestCaseDto> getTestCaseById(@PathVariable Long id) {
        return new ResponseEntity<>(testCaseService.getTestCaseById(id), HttpStatus.OK);

    }

    // Create a new TestCase
    @PostMapping
    public ResponseEntity<TestCaseDto> createTestCase(@RequestBody TestCaseDto testCase) {
        System.out.println("test case controller" + testCase);
        TestCaseDto createdTestCase = testCaseService.createTestCase(testCase);
        return new ResponseEntity<>(createdTestCase, HttpStatus.CREATED);
    }

    // Update an existing TestCase
    @PutMapping("/{id}")
    public ResponseEntity<TestCaseDto> updateTestCase(@PathVariable Long id, @RequestBody TestCaseDto testCase) {
        TestCaseDto updatedTestCase = testCaseService.updateTestCase(id, testCase);
        return new ResponseEntity<>(updatedTestCase, HttpStatus.OK);
    }

    // Delete a TestCase by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable Long id) {
        testCaseService.deleteTestCase(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
