package com.howiranyourtests.script.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "script_action")
public class ScriptAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action; // e.g., "click", "enter-text", "assert-text"
    private String locatorType; // e.g., "id", "xpath", "className"
    private String locator; // e.g., "btn-test", "//div/input"
    private String value; // Optional, for actions like "enter-text" or "assert-text"

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
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

    @Override
    public String toString() {
        return "ScriptAction{" +
                "value='" + value + '\'' +
                ", locatorType='" + locatorType + '\'' +
                ", locator='" + locator + '\'' +
                ", action='" + action + '\'' +
                ", id=" + id +
                '}';
    }
}
