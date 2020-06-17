package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Teacher {

    private int teacher_id;

    private String password;

    private String teacher_name;

}
