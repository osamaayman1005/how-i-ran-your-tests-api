package com.howiranyourtests.automation.script.dto;

import com.howiranyourtests.automation.script.model.Script;
import com.howiranyourtests.automation.script.model.ScriptAction;
import com.howiranyourtests.enums.Action;
import com.howiranyourtests.enums.LocatorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScriptActionDto {
    private Long id;
    private Integer order;
    private Action action;
    private LocatorType locatorType;
    private String locator;
    private String value;

    public ScriptActionDto(ScriptAction entity){
        this.order = entity.getOrder();
        this.action = entity.getAction();
        this.locatorType = entity.getLocatorType();
        this.locator = entity.getLocator();
        this.value = entity.getValue();
    }
    public ScriptAction toEntity(Script script){
        ScriptAction entity = new ScriptAction();
        entity.setId(this.id);
        entity.setOrder(this.order);
        entity.setAction(this.action);
        entity.setLocatorType(this.locatorType);
        entity.setLocator(this.locator);
        entity.setValue(this.value);
        entity.setScript(script);
        return entity;
    }
}
