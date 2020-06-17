package com.example.demo.mapper;

import com.example.demo.model.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper{

    List<Teacher> findAll();

    void save(Teacher t);

    Teacher findById(Integer teacherId);
}
