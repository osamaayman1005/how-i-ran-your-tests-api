package com.howiranyourtests.testCase.service;

import com.howiranyourtests.ai.service.AiService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;
    private final FeatureService featureService;
    private final ScriptService scriptService;
    private final AiService aiService;

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
        if (testCaseDto.getStatus() == null) {
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
        if (testCaseDto.getScript() != null) {
            testCase.setScript(testCaseDto.getScript().toEntity());
        }
        return new TestCaseDto(testCaseRepository.save(testCase));
    }

    // Delete a TestCase by ID
    public void deleteTestCase(Long id) {
        TestCase testCase = testCaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TestCase not found with id: " + id));
        testCaseRepository.delete(testCase);
    }

    public void updateTestCaseStatus(Long testCaseId, Status status) {
        TestCase testCase = testCaseRepository.findById(testCaseId)
                .orElseThrow(() -> new RuntimeException("TestCase not found"));
        testCase.setStatus(status);
        testCaseRepository.save(testCase);
    }

    public List<TestCaseDto> generateTestCases(Long featureId) {
        StringBuilder promptBuilder = new StringBuilder();
        Feature feature = featureService.getFeatureById(featureId);
        promptBuilder.append("Create full test cases for the following main user story (feature). ");
        promptBuilder.append("Please list each test case for the main user story on a new line, starting with 'Test Case: '. \n\n");
        promptBuilder.append("User Story (Feature) name: \"").append(feature.getName()).append("\"\n\n");
        if (feature.getDescription() != null && !feature.getDescription().isBlank()) {
            promptBuilder.append("User Story (Feature) description: \"").append(feature.getDescription()).append("\"\n\n");
        }
        promptBuilder.append("This User Story (Feature) is under an epic: \"").append(feature.getEpic().getName()).append("\"\n\n");
        if (feature.getTestCases() != null && !feature.getTestCases().isEmpty()) {
            promptBuilder.append("This Feature already has the following Test Cases and you should create" +
                    " new test cases and not include the existing ones: \n");

            feature.getTestCases().forEach((testCase ->
                    promptBuilder.append("Test Case: ").append(testCase.getName()).append('\n')
            ));
        }
        promptBuilder.append("Now, based on the user story (Feature) and the context i provided, list the test cases for the user story.");
        String finalPrompt = promptBuilder.toString();
        String aiResponse = aiService.chat(finalPrompt);
        if (aiResponse == null || aiResponse.trim().isEmpty()) {
            return List.of();
        }

        return Arrays.stream(aiResponse.split("\\r?\\n"))
                .map(String::trim)
                .filter(line -> !line.isEmpty() && line.toLowerCase().startsWith("test case:"))
                .map(line -> {
                    String testCaseName = line.substring("Test Case: ".length()).trim();
                    TestCaseDto testCase = new TestCaseDto(null, testCaseName, null, Status.TODO, null,
                            feature.getId(), feature.getEpic().getId(), null, null);
                    return createTestCase(testCase);
                })
                .collect(Collectors.toList());
    }

}
