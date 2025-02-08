package com.howiranyourtests.testCase.model;

import com.howiranyourtests.global.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class Feature extends BaseEntity {

    private String name;
    private String url;
    @ManyToOne
    @JoinColumn(name = "epic_id")
    private Epic epic;

}
