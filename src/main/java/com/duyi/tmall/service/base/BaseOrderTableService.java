package com.duyi.tmall.service.base;

import com.duyi.tmall.bean.Cart;
import com.duyi.tmall.bean.OrderItem;
import com.duyi.tmall.bean.OrderTable;
import com.duyi.tmall.bean.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 表单信息
 */
public interface BaseOrderTableService {
    /**
     * 添加一行数据
     * @param orderTable 订单表
     */
    void add(OrderTable orderTable);

    /**
     * 设计一个方法获取全部的页码数据
     * @param pageNum 前端传来的页码
     * @return 返回页码的所有数据
     */
    Pagination getList(int pageNum);

    /**
     * 计算表单商品总价
     * @param items 商品表单集合
     */
    void totalPrice(List<OrderItem> items);

    /**
     * 将集合中的购物车里的商品 存入一个商品表单中
     * @param list 购物车集合
     * @param orderTable 表单对象
     */
    void createOrder(List<Cart> list ,OrderTable orderTable);

    /**
     * 通过id 查询一个订单
     * @param oid 订单id
     * @return 返回订单对象
     */
    OrderTable getOrderTable(int oid);

    /**
     * 通过id 查询一个订单
     * @param oid 订单id
     * @return 返回订单对象
     */
    OrderTable query(int oid);

    /**
     * 根据用户id 查询当前用户的所有订单
     * @param uid 用户id
     * @return 返回所有订单
     */
    List<OrderTable> list(int uid);
    /**
     * 根据订单id  更改对应的订单对象
     * @param oid 对象id
     */
    void update(int oid);

    /**
     * 根据订单id  更改对应的订单对象状态
     * @param oid 对象id
     */
    void affirm(int oid);

}
