package com.example.demo.service;

import com.example.demo.mapper.HomeworkMapper;
import com.example.demo.mapper.StudentHomeworkMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Homework;
import com.example.demo.model.Student;
import com.example.demo.model.StudentHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentHomeworkService {
    @Autowired
    final StudentHomeworkMapper studenthomeworkMapper;

    @Autowired
    final StudentMapper studentMapper;

    @Autowired
    final HomeworkMapper homeworkMapper;
    public StudentHomeworkService(StudentHomeworkMapper studenthomeworkMapper,StudentMapper studentMapper,HomeworkMapper homeworkMapper) {
        this.studenthomeworkMapper = studenthomeworkMapper;
        this.homeworkMapper=homeworkMapper;
        this.studentMapper=studentMapper;
    }

    //添加学生作业
    public boolean addStudentHomework(StudentHomework sh) {
        try{
            //获取系统当前时间
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            //将新的学生作业信息补充完整
            Homework hw=homeworkMapper.findById(sh.getHomework_id());
            sh.setHomework_title(hw.getHomework_title());
            sh.setUpdate_time(currentTime);
            //判断该学生以及该作业是否存在
            boolean isstudent = false;
            boolean ishomework = false;
            boolean isshomework=false;

            List<Student> slist = studentMapper.findAll();
            List<Homework> hlist = homeworkMapper.findAll();
            List<StudentHomework> shlist=studenthomeworkMapper.findAll();

            for (Student s : slist) {
                if (s.getStudent_id() == sh.getStudent_id()) {
                    isstudent = true;
                    break;
                }
            }
            for (Homework h : hlist) {
                if (h.getHomework_id() == sh.getHomework_id()) {
                    ishomework = true;
                    break;
                }
            }

            for(StudentHomework sho:shlist){
                if(sho.getHomework_id()==sh.getHomework_id()&&sho.getStudent_id()==sh.getStudent_id()){
                    isshomework=true;
                    break;
                }
            }
            //如果学生或者不存在或者作业不存在均返回FALSE
            if (isstudent == false || ishomework == false) {
                return false;
            }else if(!isshomework){
                studenthomeworkMapper.save(sh);
                System.out.println(sh.toString());
                return true;
            }else{
                studenthomeworkMapper.update(sh);
                System.out.println(sh.toString());
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //获取所有学生提交作业
    public  List<StudentHomework> selectAll() {

     return studenthomeworkMapper.findAll();
    }


    //获取某一次所有学生提交作业
    public  List<StudentHomework> selectshomeworkbyid(int id) {

        return studenthomeworkMapper.findAllById(id);
    }
}