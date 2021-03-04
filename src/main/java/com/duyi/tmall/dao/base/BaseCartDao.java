package com.duyi.tmall.dao.base;

import com.duyi.tmall.bean.Cart;

import java.util.List;

/**
 * @author 购物车dao
 */
public interface BaseCartDao extends BaseBeanDao{
    /**
     * 根据用户查询多个购物车
     * @param uid 用户id
     * @return 返回当前用户的所有购物车
     */
    List<Cart> list(int uid);

}
