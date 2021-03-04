package com.duyi.tmall.service.base;

import com.duyi.tmall.bean.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 订单商品表
 */
public interface BaseOrderItemService {
    /**
     * 添加商品表单
     * @param orderItem 商品表达信息对象
     */
    void add(OrderItem orderItem);

    /**
     * 根据订单表查询对应的所有商品表单
     * @param oid 表单id
     * @return 返回对应所有的商品表单集合
     */
    List<OrderItem> query(int oid);
    /**
     * 计算商品表单总价
     * @param count 商品数量
     *  @param price 商品价格
     * @return 返回总价
     */
    BigDecimal totalPrice(int count, BigDecimal price);

    /**
     * 根据商品表单集合 查询商品的总数量
     * @param orderItems 商品表单集合
     * @return 返回商品数量
     */
    int orderItemCount(List<OrderItem> orderItems);

    /**
     * 根据商品订单id查询商品订单催下
     * @param id 订单id
     * @return 返回商品订单
     */
    OrderItem get(int id);
}
