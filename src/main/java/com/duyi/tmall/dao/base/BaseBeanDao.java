package com.duyi.tmall.dao.base;

import java.util.List;

/**
 * @author 通用方法 接口
 */
public interface BaseBeanDao {
    /**
     * 插入一条数据
     * @param object 插入对象数据
     * @return 返回插入的 id
     */
    Integer insert(Object object);

    /**
     * 根据id查询一个对象
     * @param id 对象id
     * @return 返回一个对象
     */
    Object query(Integer id);

    /**
     *查询数量
     * @return 返回数量
     */
    Integer count();

    /**
     * 修改一条信息
     * @param obj 修改后的对象
     *
    void update(Object obj);

    /**
     * 删除一条信息
     * @param obj 删除的对象 | id
     *
     */
    void del(Object obj);

    /**
     * 查询某对线的所有信息
     * @return 放回所有对象
     */
    List<?> findAll();

    /**
     * 获取分页数据
     * @param first
     * @param max
     * @return
     */
    List<?> getList(int first,int max);

    /**
     * 修改数据
     * @param obj 对象
     */
    void update(Object obj);
}
