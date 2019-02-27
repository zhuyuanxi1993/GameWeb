package com.zyx.controller;

import com.zyx.model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {
    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public String login(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        try {
            if(bindingResult.hasErrors()){
                return "login";
            }
            //使用权限工具进行认证，登录成功后跳到 shiroFilter bean 中定义的 successUrl
            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
            return "redirect:index";
        } catch (AuthenticationException e) {
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:login";
        }
    }
    /**
     * 注销登录
     */
    @RequestMapping（"/logout")
    public String logout(RedirectAttributes redirectAttributes ){
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }
}
