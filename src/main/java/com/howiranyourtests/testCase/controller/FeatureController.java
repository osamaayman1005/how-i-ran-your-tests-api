package com.howiranyourtests.testCase.controller;

import com.howiranyourtests.testCase.model.Feature;
import com.howiranyourtests.testCase.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/features")
public class FeatureController {

    private final FeatureService featureService;

    // Get all Features
    @GetMapping
    public ResponseEntity<List<Feature>> getAllFeatures(@RequestParam(required = false) Long epicId) {
        List<Feature> features = featureService.getAllFeatures(epicId);
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    // Get Feature by ID
    @GetMapping("/{id}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable Long id) {
        return  new ResponseEntity<>(featureService.getFeatureById(id), HttpStatus.OK);
    }

    // Create a new Feature
    @PostMapping
    public ResponseEntity<Feature> createFeature(@RequestBody Feature feature) {

        Feature createdFeature = featureService.createFeature(feature);
        return new ResponseEntity<>(createdFeature, HttpStatus.CREATED);
    }

    // Update an existing Feature
    @PutMapping("/{id}")
    public ResponseEntity<Feature> updateFeature(@PathVariable Long id, @RequestBody Feature featureDetails) {
        Feature updatedFeature = featureService.updateFeature(id, featureDetails);
        return new ResponseEntity<>(updatedFeature, HttpStatus.OK);
    }

    // Delete a Feature by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long id) {
        featureService.deleteFeature(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}