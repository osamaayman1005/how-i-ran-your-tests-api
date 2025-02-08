package com.howiranyourtests.automation.script.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howiranyourtests.enums.Action;
import com.howiranyourtests.enums.LocatorType;
import com.howiranyourtests.global.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "script_action")
public class ScriptAction extends BaseEntity {

    @Column(name = "action_order")
    private Integer order;
    @Enumerated(EnumType.STRING)
    private Action action;
    @Enumerated(EnumType.STRING)
    private LocatorType locatorType;
    private String locator;
    private String value;

    @ManyToOne
    @JoinColumn(name = "script_id")
    @JsonIgnore
    private Script script;

}
