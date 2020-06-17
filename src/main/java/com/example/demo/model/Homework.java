package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Homework {

    private int homework_id;

    private String homework_title;

}
