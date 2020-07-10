package com.duyi.tmall.action.admin;

import com.duyi.tmall.bean.Category;
import com.duyi.tmall.bean.Pagination;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.service.imp.CategoryServiceImp;
import com.duyi.tmall.service.imp.ProductServiceImp;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author 商品
 */
@ParentPackage("struts-default")
@Getter
@Setter
@Namespace("/admin/product")
@Results({
        @Result(name = "list",location = "/admin/listProduct.jsp"),
        @Result(name = "delete",location = "list?&category.id=${product.category.id}",type = "redirect"),
        @Result(name = "edit",location = "/admin/editProduct.jsp"),
        @Result(name = "update",location = "list?category.id=${product.category.id}",type = "redirect"),
        @Result(name = "add",location = "list?category.id=${product.category.id}",type = "redirect")

})
public class ProductAction extends ActionSupport {
    @Autowired
    private ProductServiceImp productServiceImp;
    @Autowired
    private CategoryServiceImp categoryServiceImp;
    private Category category ;
    private Pagination pagination = new Pagination();
    private Product product;
    /**
     * 主页
     * @return 根据条件返回数据
     */
    @Action("list")
    public String list(){
        pagination = productServiceImp.getList(category.getId(),pagination.getPageNum());
        category = categoryServiceImp.query(category.getId());
        return "list";
    }

    /**
     * 删除一条商品数据
     * @return
     */
    @Action("delete")
    public String delete(){
        productServiceImp.remove(product);
        return "delete";
    }

    /**
     * 展示商品数据
     * @return
     */
    @Action("edit")
    public String edit(){
        category = categoryServiceImp.query(category.getId());
        product = productServiceImp.query(product.getId());
        return "edit";
    }

    /**
     * 修改商品数据
     * @return
     */
    @Action("update")
    public String update(){
        productServiceImp.update(product);
        return "update";
    }

    /**
     * 插入一条信息
     * @return
     */
    @Action("add")
    public String add(){
        category = categoryServiceImp.query(product.getCategory().getId());
        productServiceImp.add(product);
        return "add";
    }
}
