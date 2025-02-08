package com.howiranyourtests.automation.script.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howiranyourtests.automation.script.model.Script;
import com.howiranyourtests.testCase.model.TestCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScriptDto {
    private Long id;
    private String url;
    private List<ScriptActionDto> actions;


    public ScriptDto(Script entity){
        this.id = entity.getId();
        this.url = entity.getUrl();
        this.actions = entity.getActions().stream()
                .map(ScriptActionDto::new).toList();
    }
    public Script toEntity(){
        Script entity = new Script();
        entity.setId(this.id);
        entity.setUrl(this.url);
        entity.setActions(this.actions.stream()
                .map(scriptActionDto -> scriptActionDto.toEntity(entity))
                .toList()
        );
        return entity;
    }
}
