package com.howiranyourtests.testCase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.howiranyourtests.global.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Entity
public class Feature extends BaseEntity {

    private String name;
    private String description;
    private String url;
    @ManyToOne
    @JoinColumn(name = "epic_id")
    private Epic epic;
    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TestCase> testCases;

}
