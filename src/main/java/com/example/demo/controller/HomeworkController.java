package com.example.demo.controller;

import com.example.demo.model.Homework;
import com.example.demo.service.HomeworkService;
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
public class HomeworkController {

    @Autowired
    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @RequestMapping(value = "/allHomework",method= RequestMethod.GET)
    public ModelAndView allHomework() {

        List<Homework> list = homeworkService.selectAllHomework();
        ModelAndView mav = new ModelAndView("allHomework.jsp");
        mav.addObject("homeworklist", list);
        return mav;
    }

    @RequestMapping("publishHomework")
    public String publishHomework(){
        return "publishHomework.jsp";
    }

    //发布新作业
    @RequestMapping(value = "publishH")
    public void publishHomework(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String str = new String(req.getParameter("homework_title"));

        Homework newHomework = Homework.builder()
                .homework_id(Integer.parseInt(req.getParameter("homework_id")))
                .homework_title(str)
                .build();

        resp.setContentType("text/html;charset=UTF-8");

        if (newHomework.getHomework_title().equals("")) {

            //显示弹窗并且当关闭弹窗后跳到指定页面
            resp.getWriter().write("<script>alert('作业标题不得为空！网页将跳转到发布界面！'); window.location='publishHomework'; window.close();</script>");
            resp.getWriter().flush();
        } else {
            if (homeworkService.addHomework(newHomework)) {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('发布成功！网页将跳转到首页！'); window.location='teachermenu'; window.close();</script>");
                resp.getWriter().flush();
            } else {
                //显示弹窗并且当关闭弹窗后跳到指定页面
                resp.getWriter().write("<script>alert('发布失败，请检查后再发布！网页将跳转到发布界面！'); window.location='publishHomework'; window.close();</script>");
                resp.getWriter().flush();
            }
        }
    }
    //所有作业
    @RequestMapping(value = "/sallHomework",method = RequestMethod.GET)
    public ModelAndView doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Homework> list = homeworkService.selectAllHomework();
        ModelAndView mav = new ModelAndView("sallHomework.jsp");
        mav.addObject("homeworklist", list);
        return mav;
    }
}
