package com.example.demo.service;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    final StudentMapper studentMapper;
    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    //获取所有学生信息
    public  List<Student> selectAllStudent() {
        return studentMapper.findAll();
    }

    //增加新的学生
    public  boolean addStudent(Student s) {
        try{
            //判断该作业是否存在
            boolean isStudent = false;

            List<Student> hlist = studentMapper.findAll();

            for (Student st : hlist) {
                if (st.getStudent_id() == s.getStudent_id()) {
                    isStudent = true;
                    break;
                }
            }
            if(isStudent){
               return false;
            }else{
                studentMapper.save(s);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //登录
    public boolean slogin(Student s){
       Student ns= studentMapper.findById(s.getStudent_id());
       System.out.print(ns.getStudent_id());
       System.out.print(s.getPassword());
       System.out.print(ns.getPassword());
        if(s.getPassword().equals(ns.getPassword())){
            return true;
        }else{
            return false;
        }
    }
}
