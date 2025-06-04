package com.howiranyourtests.automation.script.model;

import com.howiranyourtests.global.model.BaseEntity;
import com.howiranyourtests.testCase.model.TestCase;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@Entity
@Table(name = "script")
public class Script extends BaseEntity {

    private String title;
    private String url;

    @OneToMany(mappedBy = "script", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ScriptAction> actions;

    @OneToOne
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;
}