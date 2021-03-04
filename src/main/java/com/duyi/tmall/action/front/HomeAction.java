package com.duyi.tmall.action.front;

import com.duyi.tmall.bean.Category;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.service.base.BaseCategoryService;
import com.duyi.tmall.service.base.BaseCommentService;
import com.duyi.tmall.service.base.BaseProductService;
import com.duyi.tmall.service.base.BaseSearchService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 主页显示数据action
 */
@Namespace("/")
@ParentPackage("struts-default")
@Results({
        @Result(name = "home",location = "/home.jsp"),
        @Result(name = "category",location = "/category.jsp"),
        @Result(name = "product",location = "/product.jsp"),
        @Result(name = "searchPage",location = "/search.jsp")

})
@Getter
@Setter
public class HomeAction extends ActionSupport {
    /**商品分类集合*/
    private List<Category> categories;
    /**一个商品对象*/
    private Product product;
    /**获取一个分类*/
    private Category category;
    /**商品集合*/
    private List<Product> products;
    /**模糊查询的字符串*/
    private String keyword;
    /**判断是降序还是升序*/
    private String sort;
    @Autowired
    private BaseSearchService searchService;
    @Autowired
    private BaseCategoryService categoryService;
    @Autowired
    private BaseProductService productService;
    @Autowired
    private BaseCommentService commentService;
    @Action("index")
    public String index(){
        //显示所有上分分类的集合
        categories = categoryService.list();
        return "home";
    }

    @Action("category")
    public String category(){
        //获取一个分类的商品

        category = categoryService.query(category.getId());
        if (keyword == null && sort == null){
            products = category.getProducts();
            return "category";
        }
        products = searchService.likeQuery(keyword,sort,category.getId());
        return "category";
    }
    @Action("product")
    public String product(){
        //获取一个对象
        product = productService.query(product.getId());
        product.setComments(commentService.list(product.getId()));
        product.setCommentCount(commentService.list(product.getId()).size());
        return "product";
    }
    @Action("search")
    public String search(){
        products = searchService.likeQuery(keyword,sort,-1);
        return "searchPage";
    }
}
