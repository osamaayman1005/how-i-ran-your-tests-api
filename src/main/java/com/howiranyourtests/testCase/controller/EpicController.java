package com.howiranyourtests.testCase.controller;

import com.howiranyourtests.testCase.model.Epic;
import com.howiranyourtests.testCase.service.EpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/epics")
public class EpicController {

    private final EpicService epicService;


    // Get all Epics
    @GetMapping
    public ResponseEntity<List<Epic>> getAllEpics() {
        List<Epic> epics = epicService.getAllEpics();
        return new ResponseEntity<>(epics, HttpStatus.OK);
    }

    // Get Epic by ID
    @GetMapping("/{id}")
    public ResponseEntity<Epic> getEpicById(@PathVariable Long id) {
        return new ResponseEntity<>(epicService.getEpicById(id), HttpStatus.OK);

    }

    // Create a new Epic
    @PostMapping
    public ResponseEntity<Epic> createEpic(@RequestBody Epic epic) {
        Epic createdEpic = epicService.createEpic(epic);
        return new ResponseEntity<>(createdEpic, HttpStatus.CREATED);
    }

    // Update an existing Epic
    @PutMapping("/{id}")
    public ResponseEntity<Epic> updateEpic(@PathVariable Long id, @RequestBody Epic epicDetails) {
        Epic updatedEpic = epicService.updateEpic(id, epicDetails);
        return new ResponseEntity<>(updatedEpic, HttpStatus.OK);
    }

    // Delete an Epic by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpic(@PathVariable Long id) {
        epicService.deleteEpic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}