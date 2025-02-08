package com.howiranyourtests.testCase.dto;

import com.howiranyourtests.automation.script.dto.ScriptDto;
import com.howiranyourtests.global.enums.Status;
import com.howiranyourtests.testCase.model.Feature;
import com.howiranyourtests.testCase.model.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseDto {
    private Long id;
    private String name;
    private String description;
    private Status status;
    private ScriptDto script;
    private Long featureId;
    private Long epicId;

    public TestCaseDto(TestCase entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.status = entity.getStatus();
        if(entity.getScript() != null) {
            this.script = new ScriptDto(entity.getScript());
        }
        this.featureId = entity.getFeature().getId();
        this.epicId = entity.getFeature().getEpic().getId();
    }
    public TestCase toEntity(Feature feature){
        TestCase entity = new TestCase();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setStatus(this.status);
        if(this.script != null){
            entity.setScript(this.script.toEntity());
        }
        entity.setFeature(feature);

        return entity;
    }
}
