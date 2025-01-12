package com.howiranyourtests.script.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "script")
public class Script {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;

    @OneToMany(mappedBy = "script", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ScriptAction> actions;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ScriptAction> getActions() {
        return actions;
    }

    public void setActions(List<ScriptAction> actions) {
        this.actions = actions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}