package com.howiranyourtests.testCase.service;

import com.howiranyourtests.testCase.model.Epic;
import com.howiranyourtests.testCase.repository.EpicRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpicService {

    private final EpicRepository epicRepository;

    // Get all Epics
    public List<Epic> getAllEpics() {
        return epicRepository.findAll();
    }

    // Get Epic by ID
    public Epic getEpicById(Long id) {
        return epicRepository.findById(id).orElseThrow(() -> new NotFoundException("Epic not found"));
    }

    // Create a new Epic
    public Epic createEpic(Epic epic) {
        return epicRepository.save(epic);
    }

    // Update an existing Epic
    public Epic updateEpic(Long id, Epic epicDetails) {
        Epic epic = epicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Epic not found with id: " + id));

        epic.setName(epicDetails.getName());
        epic.setUrl(epicDetails.getUrl());
        // Add other fields to update as needed

        return epicRepository.save(epic);
    }

    // Delete an Epic by ID
    public void deleteEpic(Long id) {
        Epic epic = epicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Epic not found with id: " + id));
        epicRepository.delete(epic);
    }
}
