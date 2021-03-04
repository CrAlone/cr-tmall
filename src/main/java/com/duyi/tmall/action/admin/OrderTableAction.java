package com.duyi.tmall.action.admin;

import com.duyi.tmall.bean.OrderTable;
import com.duyi.tmall.bean.Pagination;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.bean.User;
import com.duyi.tmall.service.base.BaseOrderTableService;
import com.duyi.tmall.service.base.BaseProductService;
import com.duyi.tmall.service.base.BaseUserService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 订单表
 */
@ParentPackage("struts-default")
@Namespace("/admin/table")
@Results({
        @Result(name = "list",location = "/admin/listOrder.jsp"),
        @Result(name = "shipments",location = "list",type = "redirect")
})
@Setter
@Getter
public class OrderTableAction extends ActionSupport {
    @Autowired
    private BaseOrderTableService baseOrderTableService;
    private Pagination pagination = new Pagination();
    private OrderTable orderTable;
    @Autowired
    private BaseOrderTableService orderTableService;
    @Action("list")
    public String table(){
        pagination = baseOrderTableService.getList(pagination.getPageNum());
        return "list";
    }
    @Action("shipments")
    public String shipments(){
        orderTableService.update(orderTable.getId());
        return "shipments";
    }
}


