package com.duyi.tmall.dao.base;


import com.duyi.tmall.dao.imp.OrderItemDaoImpl;

import java.util.List;

/**
 * @author 订单商品表 dao
 */
public interface BaseOrderItemDao extends BaseBeanDao{
    /**
     * 根据订单id查询所有对应的商品表单
     * @param id 订单id
     * @return 返回对应的商品表单集合
     */
    List<OrderItemDaoImpl> getList(int id);

}
