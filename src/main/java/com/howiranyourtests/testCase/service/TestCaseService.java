package com.howiranyourtests.testCase.service;

import com.howiranyourtests.automation.script.dto.ScriptDto;
import com.howiranyourtests.automation.script.model.Script;
import com.howiranyourtests.automation.script.service.ScriptService;
import com.howiranyourtests.global.enums.Status;
import com.howiranyourtests.testCase.dto.TestCaseDto;
import com.howiranyourtests.testCase.model.Feature;
import com.howiranyourtests.testCase.model.TestCase;
import com.howiranyourtests.testCase.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final FeatureService featureService;
    private final ScriptService scriptService;

    // Get all TestCases (optionally filtered by featureId)
    public List<TestCaseDto> getAllTestCases(Long featureId) {
        if (featureId != null) {
            return testCaseRepository.findByFeatureId(featureId).stream().map(TestCaseDto::new).toList();
        } else {
            return testCaseRepository.findAll().stream().map(TestCaseDto::new).toList();
        }
    }


    // Get TestCase by ID
    public TestCaseDto getTestCaseById(Long id) {
        TestCase testCase = testCaseRepository.findById(id).orElseThrow(RuntimeException::new);
        return new TestCaseDto(testCase);
    }

    // Create a new TestCase
    public TestCaseDto createTestCase(TestCaseDto testCaseDto) {
        Feature feature = featureService.getFeatureById(testCaseDto.getFeatureId());
        if(testCaseDto.getStatus() == null){
            testCaseDto.setStatus(Status.TODO);
        }
        ScriptDto scriptDto = testCaseDto.getScript();
        testCaseDto.setScript(null);
        TestCase savedTestCase = testCaseRepository.save(testCaseDto.toEntity(feature));

        if (scriptDto != null) {
            Script script = scriptDto.toEntity();
            script.setTestCase(savedTestCase);
            scriptService.saveScript(script);
        }

         return new TestCaseDto(savedTestCase);
    }

    // Update an existing TestCase
    public TestCaseDto updateTestCase(Long id, TestCaseDto testCaseDto) {
        TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TestCase not found with id: " + id));
        testCase.setName(testCaseDto.getName());
        testCase.setDescription(testCaseDto.getDescription());
        testCase.setStatus(testCaseDto.getStatus());
        testCase.setScript(testCaseDto.getScript().toEntity());
        return new TestCaseDto(testCaseRepository.save(testCase));
    }

    // Delete a TestCase by ID
    public void deleteTestCase(Long id) {
        TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TestCase not found with id: " + id));
        testCaseRepository.delete(testCase);
    }
}