package com.duyi.tmall.action.admin;

import com.duyi.tmall.bean.Image;
import com.duyi.tmall.bean.Pagination;
import com.duyi.tmall.bean.User;
import com.duyi.tmall.service.base.BaseUserService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author 用户控制层
 */
@ParentPackage("struts-default")
@Namespace("/admin/user")
@Results({
        @Result(name = "list",location = "/admin/listUser.jsp")
})
@Getter
@Setter
public class UserAction extends ActionSupport {
    @Autowired
    private BaseUserService baseUserService;
    private User user;
    private Pagination pagination = new Pagination();
    @Action("list")
    public String list(){
        pagination = baseUserService.getList(pagination.getPageNum());
        return "list";
    }

}

