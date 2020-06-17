package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;

    }

    @RequestMapping("allStudent")
    public ModelAndView allStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Student> list = studentService.selectAllStudent();
        ModelAndView mav = new ModelAndView("allStudent.jsp");
        mav.addObject("studentlist", list);
        return mav;
    }
    @RequestMapping("addStudent")
    public String addStudent(){
        return "addStudent.jsp";
    }
    @RequestMapping(value = "addS", method=RequestMethod.GET)
    public void addStudent(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        int id=Integer.parseInt(req.getParameter("student_id"));
        //编码问题需要转换
        String str = new String(req.getParameter("student_name"));
        String password=req.getParameter("password");
        //从该界面获取到新的学生信息
        Student newStudent=Student.builder()
                .student_id(id)
                .password(password)
                .student_name(str)
                .build();

        resp.setContentType("text/html;charset=UTF-8");

        try {
            if (newStudent.getStudent_name().equals("")||newStudent.getPassword().isEmpty()) {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('姓名或密码不能为空，请检查后再添加!网页将跳转到添加界面！'); window.location='addStudent'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                if (studentService.addStudent(newStudent)) {

                    //显示弹窗并且当关闭弹窗后跳到指定页面
                    resp.getWriter().write("<script>alert('添加成功！'); window.location='teachermenu'; window.close();</script>");
                    resp.getWriter().flush();

                } else {
                    //显示弹窗并且当关闭弹窗后跳到指定页面
                    resp.getWriter().write("<script>alert('添加失败，请检查信息后再添加！网页将跳转到添加界面！'); window.location='addStudent'; window.close();</script>");
                    resp.getWriter().flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
