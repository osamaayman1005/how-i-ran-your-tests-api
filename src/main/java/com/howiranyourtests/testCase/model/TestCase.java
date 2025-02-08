package com.howiranyourtests.testCase.model;

import com.howiranyourtests.automation.script.model.Script;
import com.howiranyourtests.global.enums.Status;
import com.howiranyourtests.global.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class TestCase extends BaseEntity {

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature feature;
    @OneToOne(mappedBy = "testCase")
    private Script script;
}
