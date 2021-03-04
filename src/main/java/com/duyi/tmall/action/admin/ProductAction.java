package com.duyi.tmall.action.admin;

import com.duyi.tmall.bean.Category;
import com.duyi.tmall.bean.Image;
import com.duyi.tmall.bean.Pagination;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.service.imp.CategoryServiceImpl;
import com.duyi.tmall.service.imp.ProductServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


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
    private ProductServiceImpl productServiceImp;
    @Autowired
    private CategoryServiceImpl categoryServiceImp;
    private Category category ;
    private Pagination pagination = new Pagination();
    private Product product;
    private List<Image> image = new ArrayList<Image>();
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
     * @return 返回页面
     */
    @Action("delete")
    public String delete(){
        productServiceImp.remove(product);
        return "delete";
    }

    /**
     * 展示商品数据
     * @return 返回页面
     */
    @Action("edit")
    public String edit(){
        category = categoryServiceImp.query(category.getId());
        product = productServiceImp.query(product.getId());
        return "edit";
    }

    /**
     * 修改商品数据
     * @return 返回页面
     */
    @Action("update")
    public String update(){
        Product product1 = productServiceImp.query(product.getId());
        product1.setName(product.getName());
        product1.setSubTitle(product.getSubTitle());
        product1.setOPrice(product.getOPrice());
        product1.setNPrice(product.getNPrice());
        product1.setStock(product.getStock());
        productServiceImp.update(product1);

        return "update";
    }
    /**
     * 插入一条信息
     * @return 返回页面
     */
    @Action("add")
    public String add(){
        category = categoryServiceImp.query(product.getCategory().getId());
        productServiceImp.add(product);
        return "add";
    }
}
