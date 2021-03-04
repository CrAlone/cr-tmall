package com.duyi.tmall.dao.base;

import com.duyi.tmall.bean.OrderTable;

import java.util.List;

/**
 * @author 订单表dao
 */
public interface BaseOrderTableDao extends BaseBeanDao{
    /**
     * 通过id 查询一个订单
     * @param oid 订单id
     * @return 返回订单对象
     */
    OrderTable getOrderTable(int oid);

    /**
     * 根据用户id 查询当前用户的所有订单
     * @param uid 用户id
     * @return 返回所有订单
     */
    List<OrderTable> list(int uid);
}
