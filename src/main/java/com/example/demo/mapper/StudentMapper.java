package com.example.demo.mapper;

import com.example.demo.model.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentMapper  {

    Student findById(Integer studentId);

    List<Student> findAll();

    void save(Student student);
}
