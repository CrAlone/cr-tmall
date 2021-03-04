package com.duyi.tmall.action.front;

import com.duyi.tmall.bean.Cart;
import com.duyi.tmall.bean.OrderItem;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.bean.User;
import com.duyi.tmall.service.base.BaseCartService;
import com.duyi.tmall.service.base.BaseImageService;
import com.duyi.tmall.service.base.BaseProductService;
import com.duyi.tmall.service.base.BaseUserService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 购物车
 */
@Namespace("/")
@ParentPackage("json-default")
@Results({
        @Result(name = "result",type = "json",params = {"root","result"}),
        @Result(name = "cartPage",location = "/cart.jsp"),
        @Result(name = "del",location = "cart",type = "redirect")

})
@Getter
@Setter
public class CartAction extends ActionSupport {
    /**商品对象*/
    private Product product;
    /**响应信息*/
    private String result;
    /**添加购物车商品数量*/
    private int num;
    /**购物车*/
    private Cart cart;
    private List<Cart> cartItems = new ArrayList<Cart>();
    @Autowired
    private BaseProductService productService;
    @Autowired
    private BaseCartService cartService;
    @Autowired
    private BaseUserService userService;
    @Autowired
    private BaseImageService imageService;
    @Action("addCart")
    public String cartLink(){
        product = productService.query(product.getId());
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        User dbUser = userService.gerUser(user.getName());
        if (cartService.joinCart(product,num,dbUser) == true){
            result = "success";
            return "result";
        }
        result = "OutOfStock";
        return "result";
    }
    @Action("cart")
    public String cart(){
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        User dbUser = userService.gerUser(user.getName());
        //根据用户id查询所有点单表 返回所有商品订单
        cartItems = cartService.getOrderItem(dbUser.getId());
        return "cartPage";
    }
    /**删除*/
    @Action("del")
    public String del(){
        cartService.del(cart.getId());
        return "del";
    }
//    /**新增*/
//    @Action("changeCartNum")
//    public String changeCartNum(){
//
//    }
}
