package com.example.demo.controller;

import com.example.demo.mapper.HomeworkMapper;
import com.example.demo.mapper.StudentHomeworkMapper;
import com.example.demo.model.Homework;
import com.example.demo.model.StudentHomework;
import com.example.demo.service.StudentHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class StudentHomeworkController {

    @Autowired
    private final StudentHomeworkService studenthomeworkService;
    @Autowired
    final HomeworkMapper homeworkMapper;


    public StudentHomeworkController(StudentHomeworkService studenthomeworkService, HomeworkMapper homeworkMapper, StudentHomeworkMapper studenthomeworkMapper) {
        this.studenthomeworkService = studenthomeworkService;
        this.homeworkMapper = homeworkMapper;
    }

    //所有学生作业
    @RequestMapping(value = "allStudentHomework", method = RequestMethod.GET)
    public ModelAndView allStudentHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<StudentHomework> list = studenthomeworkService.selectAll();
        ModelAndView mav = new ModelAndView("allStudentHomework.jsp");
        mav.addObject("studentHomeworklist", list);
        return mav;
    }

    @RequestMapping("submitallHomework")
    public String submitallHomework() {
        return "submitallHomework.jsp";
    }


    //提交作业
    @RequestMapping(value = "submitallH")
    public void submitallHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取系统当前时间
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        //将新的学生作业信息补充完整

        //编码问题需要转换
        String str = new String(req.getParameter("homework_content"));


        Homework hw = homeworkMapper.findById(Integer.parseInt(req.getParameter("homework_id")));

        StudentHomework newsHomework = StudentHomework.builder()
                .homework_id(Integer.parseInt(req.getParameter("homework_id")))
                .student_id(Integer.parseInt(req.getParameter("student_id")))
                .homework_content(str)
                .homework_title(hw.getHomework_title())
                .update_time(currentTime)
                .build();
        System.out.print(newsHomework.toString());
        resp.setContentType("text/html;charset=UTF-8");
        if (studenthomeworkService.addStudentHomework(newsHomework)) {
            //显示弹窗并且当关闭弹窗后跳到指定页面
            resp.getWriter().write("<script>alert('提交成功！网页将跳转到首页！'); window.location='studentmenu'; window.close();</script>");
            resp.getWriter().flush();
        } else {
            //显示弹窗并且当关闭弹窗后跳到指定页面
            resp.getWriter().write("<script>alert('提交失败，请仔细检查后再提交！网页将跳转到提交界面！'); window.location='submitHomework'; window.close();</script>");
            resp.getWriter().flush();
        }
    }

    //判断是否提交
    static boolean isfirst = true;

    @RequestMapping("submitHomework")
    public ModelAndView submitHome(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mav;
        int id = Integer.parseInt(req.getParameter("id"));
        mav = new ModelAndView("submitHomework.jsp");
        mav.addObject("id", id);
        isfirst = false;
        return mav;
    }


    @RequestMapping("submitH")
    public ModelAndView submitHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ModelAndView mav;
        //编码问题需要转换
        String str = new String(req.getParameter("homework_content"));
        //获取系统当前时间
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);

        Homework hw = homeworkMapper.findById(Integer.parseInt(req.getParameter("homework_id")));

        //将新的学生作业信息补充完整
        //将新的学生作业信息实体化
        StudentHomework newsHomework = StudentHomework.builder()
                .student_id(Integer.parseInt(req.getParameter("student_id")))
                .homework_id(Integer.parseInt(req.getParameter("homework_id")))
                .homework_content(str)
                .update_time(currentTime)
                .homework_title(hw.getHomework_title())
                .build();

        resp.setContentType("text/html;charset=UTF-8");
        if (studenthomeworkService.addStudentHomework(newsHomework)) {
            //显示弹窗并且当关闭弹窗后跳到指定页面
            mav = new ModelAndView("studentmenu.jsp");
            resp.getWriter().write("<script>alert('提交成功！网页将跳转到首页！'); window.location='studentmenu'; window.close();</script>");
            resp.getWriter().flush();
        } else {
            mav = new ModelAndView("submitHomework.jsp");
            //显示弹窗并且当关闭弹窗后跳到指定页面
            resp.getWriter().write("<script>alert('提交失败，请仔细检查后再提交！网页将跳转到提交界面！'); window.location='sallHomework'; window.close();</script>");
            resp.getWriter().flush();
        }
        isfirst = true;
        return mav;
    }

    //选取某一次作业
    @RequestMapping(value = "OneHomework", method = RequestMethod.GET)
    public ModelAndView oneHomework(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        List<StudentHomework> list = studenthomeworkService.selectshomeworkbyid(id);

        ModelAndView mav = new ModelAndView("OneHomework.jsp");
        mav.addObject("oneHomeworklist", list);
        return mav;
    }

}