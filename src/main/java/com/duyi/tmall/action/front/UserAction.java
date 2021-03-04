package com.duyi.tmall.action.front;



import cn.hutool.json.JSONUtil;
import com.duyi.tmall.bean.Category;
import com.duyi.tmall.bean.User;
import com.duyi.tmall.service.base.BaseCategoryService;
import com.duyi.tmall.service.base.BaseUserService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author 登录注册控制层
 */
@ParentPackage("json-default")
@Namespace("/")
@Results({
        @Result(name = "login",location = "/login.jsp"),
        @Result(name = "register",location = "/register.jsp"),
        @Result(name = "registerSuccess",location = "/registerSuccess.jsp"),
        @Result(name = "home",location = "/home.jsp"),
        @Result(name = "success",type = "json",params = {"root","data"}),
        @Result(name = "forget",location = "/forgetPass.jsp"),
        @Result(name = "forgetPass",location = "/forgetPass.jsp"),
        @Result(name = "pass",location = "/newPass.jsp"),
        @Result(name = "newPass",location = "forget",type = "redirect")
})
@Getter
@Setter
public class UserAction extends ActionSupport {
    private User user = new User();
    /**
     * ajax返回的响应信息
     */
    private String data;
    @Autowired
    private BaseUserService userService;
    @Autowired
    private BaseCategoryService categoryService;
    /**
     * 响应信息
     */
    private String msg;
    /**商品分类集合*/
    private List<Category> categories;
    private String token;
    @Action("login")
    public String login(){
        return "login";
    }
    @Action("loginIn")
    public String loginIn(){
        if (userService.login(user)){
            //显示所有上分分类的集合
            categories = categoryService.list();
            msg = "登录成功";
            //将用户存入session中
            ServletActionContext.getRequest().getSession().setAttribute("user",user);
            return "home";
        }
        msg = "用户名或密码不正确";
        return "login";
    }


    @Action("register")
    public String register(){
        return "register";
    }


    @Action("regAdd")
    public String regAdd(){
        if (!userService.get(user)){
            return "register";
        }
        return "registerSuccess";
    }
    @Action("sendMail")
    public String sendMail(){
        data = JSONUtil.toJsonStr(userService.sendEmail(user));

        return "success";
    }
    @Action("forgetPass")
    public String forgetPass(){
        return "forgetPass";
    }
    @Action("forget")
    public String forget(){
        if (user != null || user.getEmail() !=null){
            String name = userService.findPassEmail(user);
            if (name != null){
                msg = "连接已发送至您的邮箱,请注意查收!";
                return "forget";
            }
        }
        msg = "邮箱不正确";
        return "forget";
    }
    @Action("newPass")
    public String pass(){
        return "pass";
    }
    @Action("pass")
    public String newPass(){
        String name = userService.verifyToken(token);
        System.out.println(name);
        if (name != null){
            user.setName(name);
            //如果不为null则调用方法 修改密码
            userService.modifyPass(user);
            System.out.println("执行了");
            msg = "更改成功,正在跳转登录页面";
            return "login";
        }
        //如果为null 则更改失败 返回到
        return "newPass";
    }
}
