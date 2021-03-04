package com.duyi.tmall.action.front;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.duyi.tmall.bean.*;
import com.duyi.tmall.service.base.*;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 商品提交表单
 */
@Namespace("/")
@ParentPackage("struts-default")
@Results({
        @Result(name = "buyOnePage",location = "/buy.jsp"),
        @Result(name = "msg",location = "/msg.jsp"),
        @Result(name = "payPage",location = "/pay.jsp"),
        @Result(name = "payedPage",location = "/payed.jsp"),
        @Result(name = "myOrderPage",location = "/myOrder.jsp"),
        @Result(name = "confirmPay",location = "/confirmPay.jsp"),
        @Result(name = "confirmedPage",location = "/confirmed.jsp"),
        @Result(name = "quit",location = "login",type = "redirect"),
        @Result(name = "commentPage",location = "/comment.jsp"),
        @Result(name = "addComment",location = "/addComment.jsp")


})
@Getter
@Setter
public class OrderAction extends ActionSupport {
    private Category category;
    /**响应信息*/
    private String msg;
    /**商品 id*/
    private Product product;
    /**立即购买  商品数量*/
    private int num;
    /**当前购物车总价*/
    private BigDecimal sum;
    /**购物车集合*/
    private List<Cart> cartList = new ArrayList<Cart>();
    /**订单表*/
    private OrderTable orderTable;
    /**当前用户的所有订单*/
    private List<OrderTable> orderTables = new ArrayList<OrderTable>();
    /**商品订单表*/
    private OrderItem orderItem ;
    /**商品评价表*/
    private Comment comment;
    @Autowired
    private BaseOrderItemService itemService;
    @Autowired
    private BaseCommentService commentService;
    @Autowired
    private BaseProductService productService;
    @Autowired
    private BaseOrderTableService orderTableService;
    @Autowired
    private BaseUserService userService;
    @Action("checkLogin")
    public String checkLogin(){
        if (!ObjectUtil.isEmpty(ServletActionContext.getRequest().getSession().getAttribute("user"))){
            msg = "success";
        }else {
            msg = "fail";
        }
        return "msg";
    }
    @Action("buyOne")
    public String buyOne(){
        //获取用户对象
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        product = productService.query(product.getId());
        Cart cart = new Cart();
        cart.setDel(0);
        cart.setCreateTime(DateUtil.date());
        cart.setProduct(product);
        cart.setNumber(num);
        cart.setUser(user);
        //前端需要的总价
        sum = product.getNPrice().multiply(new BigDecimal(num));
        cartList.add(cart);
        ServletActionContext.getRequest().getSession().setAttribute("cartList",cartList);
        return "buyOnePage";
    }
    @Action("createOrder")
    public String pay(){
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        User daUser = userService.gerUser(user.getName());
        //购物车集合
        cartList = (List<Cart>) ServletActionContext.getRequest().getSession().getAttribute("cartList");
        orderTable.setUser(daUser);
        orderTableService.createOrder(cartList,orderTable);
        return "payPage";
    }
    @Action("payed")
    public String payed(){
        orderTable = orderTableService.getOrderTable(orderTable.getId());
        return "payedPage";
    }
    @Action("myOrder")
    public String myOrderPage(){
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        User daUser = userService.gerUser(user.getName());
        orderTables = orderTableService.list(daUser.getId());
        return "myOrderPage";
    }
    /**确认收货*/
    @Action("confirmPay")
    public String confirmPay(){
        orderTableService.affirm(orderTable.getId());
        orderTable = orderTableService.query(orderTable.getId());
        return "confirmPay";
    }
    @Action("confirmed")
    public String confirmed(){
        return "confirmedPage";
    }
    /**退出*/
    @Action("quit")
    public String quit(){
        ServletActionContext.getRequest().getSession().removeAttribute("user");
        return "quit";
    }
    /**评价*/
    @Action("comment")
    public String comment(){
        orderItem = itemService.get(orderItem.getId());
        return "commentPage";
    }
    /**添加评论*/
    @Action("addComment")
    public String addComment(){
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        User daUser = userService.gerUser(user.getName());
        comment.setUser(daUser);
        comment.setCreateTime(DateUtil.date());
        comment.setDel(0);
        commentService.add(comment);
        return "addComment";
    }
}


