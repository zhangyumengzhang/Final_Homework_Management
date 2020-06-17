package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
     private final StudentService studentService;
    @Autowired
     private final TeacherService teacherService;

    public LoginController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @RequestMapping("/")
    public String test(){
        System.out.println("q12345uyd");
        return "login.jsp";
    }

    @RequestMapping("teachermenu")
    public String teachermenu(){
        return "teachermenu.jsp";
    }

    @RequestMapping("studentmenu")
    public String studentmenu(){
        return "studentmenu.jsp";
    }


    @RequestMapping("register")
    public String register(){
        return "register.jsp";
    }
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userType = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        String password =req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");

        if(userType.equals("teacher")){
            Teacher teach=Teacher.builder()
                    .teacher_id(id)
                    .password(password)
                    .build();

            if(teacherService.tlogin(teach)){
                resp.getWriter().write("<script>alert('登陆成功！'); window.location='teachermenu'; window.close();</script>");
                resp.getWriter().flush();
            }else{
                System.out.println(teach.getTeacher_id());
                System.out.println(teach.getPassword());
                resp.getWriter().write("<script>alert('用户名或密码错误，请检查后再登录!网页将跳转到登录界面！'); window.location='/'; window.close();</script>");
                resp.getWriter().flush();
            }
        }else if(userType.equals("student")){

            Student stud=Student.builder()
                    .student_id(id)
                    .password(password)
                    .build();

            if(studentService.slogin(stud)){
                resp.getWriter().write("<script>alert('登陆成功！'); window.location='studentmenu'; window.close();</script>");
                resp.getWriter().flush();
            }else{
                resp.getWriter().write("<script>alert('用户名或密码错误，请检查后再登录!网页将跳转到登录界面！'); window.location='/'; window.close();</script>");
                resp.getWriter().flush();
            }
        }
    }

    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userType = req.getParameter("type");
        int id = Integer.parseInt(req.getParameter("id"));
        String name=req.getParameter("name");
        String password =req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");
        if(userType.equals("teacher")){
            Teacher teach=Teacher.builder()
                    .teacher_id(id)
                    .password(password)
                    .teacher_name(name)
                    .build();

            if(teacherService.addTeacher(teach)){
                resp.getWriter().write("<script>alert('注册成功！'); window.location='/'; window.close();</script>");
                resp.getWriter().flush();
            }else{
                resp.getWriter().write("<script>alert('该用户已存在!网页将跳转到登录界面！'); window.location='/'; window.close();</script>");
                resp.getWriter().flush();
            }
        }else if(userType.equals("student")){
            Student stud=Student.builder()
                    .student_id(id)
                    .password(password)
                    .student_name(name)
                    .build();
            if(studentService.addStudent(stud)){
                resp.getWriter().write("<script>alert('注册成功！'); window.location='/'; window.close();</script>");
                resp.getWriter().flush();
            }else{
                resp.getWriter().write("<script>alert('该用户已存在!网页将跳转到登录界面！'); window.location='/'; window.close();</script>");
                resp.getWriter().flush();
            }
        }
    }
}
