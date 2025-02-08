package com.howiranyourtests.testCase.model;

import com.howiranyourtests.global.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class Epic extends BaseEntity {

    private String name;
    private String url;
}
