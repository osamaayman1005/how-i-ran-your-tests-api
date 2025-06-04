package com.howiranyourtests.testCase.service;

import com.howiranyourtests.testCase.model.Epic;
import com.howiranyourtests.testCase.model.Feature;
import com.howiranyourtests.testCase.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final EpicService epicService;

    // Get all Features
    public List<Feature> getAllFeatures(Long epicId) {
        if (epicId != null) {
            return featureRepository.findByEpicId(epicId);
        } else {
            return featureRepository.findAll();
        }
    }
    // Get Feature by ID
    public Feature getFeatureById(Long id) {
        return featureRepository.findById(id).orElseThrow(()-> new RuntimeException("Feature not found"));
    }

    // Create a new Feature
    public Feature createFeature(Feature feature) {
        Epic epic = epicService.getEpicById(feature.getEpic().getId());
        feature.setEpic(epic);
        return featureRepository.save(feature);
    }

    // Update an existing Feature
    public Feature updateFeature(Long id, Feature featureDetails) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature not found with id: " + id));

        feature.setName(featureDetails.getName());
        feature.setUrl(featureDetails.getUrl());
        feature.setDescription(featureDetails.getDescription());

        return featureRepository.save(feature);
    }

    // Delete a Feature by ID
    public void deleteFeature(Long id) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feature not found with id: " + id));
        featureRepository.delete(feature);
    }
}