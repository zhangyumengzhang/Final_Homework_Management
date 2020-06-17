package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Student {

    private int student_id;

    private String password;

    private String student_name;

}
