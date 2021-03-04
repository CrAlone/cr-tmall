package com.duyi.tmall.service.base;


import com.duyi.tmall.bean.Cart;
import com.duyi.tmall.bean.OrderItem;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.bean.User;

import java.util.List;

public interface BaseCartService {
    /**
     * 用来判断商品库存是否能够满足 加入购物车的数量
     * @param product 商品对象
     * @param num 添加数量
     * @param user 用户
     * @return true 能 false 不能
     */
    boolean joinCart(Product product, int num, User user);

    /**
     * 根据用户id查询所有商品订单
     * @param uid 用户id
     * @return 返回所有商品订单
     */
    List<Cart> getOrderItem(int uid);

    /**
     * 根据购物车删除该购物车
     * @param cid 购物车id
     */
    void del(int cid);
}
