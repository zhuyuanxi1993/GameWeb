package com.zyx.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
public class Login {
    @RequestMapping("/Login1")
    public String login1() {
        ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("time","time");
        modelAndView.addObject("m",map);
        modelAndView.addObject("user","user");

        return "index";
    }

    @RequestMapping("/Login2")
    public ModelAndView login2(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("time","time");
        modelAndView.addObject("m",map);
        modelAndView.addObject("user","user");
        modelAndView.setViewName("index");
        model.addAttribute("back", "back");
        return modelAndView;
    }

    @RequestMapping("/Login3")
    public String login3(Map map) {
        map.put("user","user");
        return "index";
    }

    @RequestMapping("/Login4")
    public String login4(ModelAndView modelAndView) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("time","time");
        modelAndView.addObject("m",map);
        modelAndView.addObject("user","user");

        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String passwd, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passwd);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("userName", "用户名错误！");
            return "login";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("passwd", "密码错误");
            return "login";
        }
        return "index";
    }
}
