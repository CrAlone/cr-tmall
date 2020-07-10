package com.duyi.tmall.action.admin;


import com.duyi.tmall.bean.Category;
import com.duyi.tmall.bean.Pagination;
import com.duyi.tmall.service.base.BaseCategoryService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * @author 商品分类的控制成
 */
@ParentPackage("struts-default")
@Namespace("/admin/category")
@Results({
        @Result(name = "success",location = "/admin/listCategory.jsp"),
        @Result(name = "delete",location = "backstage",type = "redirect"),
        @Result(name = "edit",location = "/admin/editCategory.jsp"),
        @Result(name = "update",location = "backstage",type = "redirect"),
        @Result(name = "add",location = "backstage",type = "redirect")
})
@Setter
@Getter
public class CategoryAction extends ActionSupport {
    private Pagination pagination = new Pagination();
    private Category category;
    /**
     * 主页信息
     */
    @Autowired
    private BaseCategoryService categoryService;
    @Action("backstage")
    public String backstage(){
        pagination = categoryService.getList(pagination.getPageNum());
        return "success";
    }
    /**
     * 删除一条数据
     */
    @Action("delete")
    public String delete(){
        categoryService.delete(category);
        return "delete";
    }

    /**
     * 查询一条信息
     * @return 返回页面
     */
    @Action("edit")
    public String edit(){
        category = categoryService.query(category.getId());
        return "edit";
    }

    /**
     * 修改一条数据
     * @return 返回页面
     */
    @Action("update")
    public String update(){
        categoryService.alter(category);
        return "update";
    }

    /**
     * 添加一条数据
     * @return 返回页面
     */
    @Action("add")
    public String add(){
        categoryService.add(category);
        return "add";
    }

}
