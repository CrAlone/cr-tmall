package com.duyi.tmall.dao.base;


import com.duyi.tmall.bean.User;

/**
 * @author 插入一条用户信息
 */
public interface BaseUserDao extends BaseBeanDao{
    /**
     * 根据姓名查询一条信息
     * @param name 姓名
     * @return 返回一个对象
     */
     User get(String name);
}
