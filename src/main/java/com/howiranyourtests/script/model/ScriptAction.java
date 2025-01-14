package com.howiranyourtests.script.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howiranyourtests.enums.Action;
import com.howiranyourtests.enums.LocatorType;
import jakarta.persistence.*;

@Entity
@Table(name = "script_action")
public class ScriptAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public LocatorType getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(LocatorType locatorType) {
        this.locatorType = locatorType;
    }

    @Override
    public String toString() {
        return "ScriptAction{" +
                "id=" + id +
                ", order=" + order +
                ", action=" + action +
                ", locatorType=" + locatorType +
                ", locator='" + locator + '\'' +
                ", value='" + value + '\'' +
                ", script=" + script +
                '}';
    }
}
