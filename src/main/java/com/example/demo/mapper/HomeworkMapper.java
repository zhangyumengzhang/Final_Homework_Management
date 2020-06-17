package com.example.demo.mapper;

import com.example.demo.model.Homework;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkMapper{

     String getTitlebyId(int homework_id);

    void save(Homework h);
    List<Homework> findAll();

    Homework findById(int homeworkId);

}
