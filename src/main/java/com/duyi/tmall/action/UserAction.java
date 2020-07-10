package com.duyi.tmall.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;


/**
 * @author 用户控制层
 */
@ParentPackage("struts-default")
@Namespace("/")

public class UserAction extends ActionSupport {

    @Action(value = "test",results = {@Result(name = "test",location = "/test.jsp")})
    public String test(){
        return "test";
    }

}

