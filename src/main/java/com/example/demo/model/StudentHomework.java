package com.example.demo.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentHomework {

    private int id;

    private int student_id;

    private int homework_id;

    private String homework_title;

    private String homework_content;

    private String update_time;

}
