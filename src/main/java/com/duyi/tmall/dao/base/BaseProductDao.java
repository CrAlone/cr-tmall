package com.duyi.tmall.dao.base;



import java.util.List;


/**
 * @author 商品信息表
 */
public interface BaseProductDao extends BaseBeanDao{
    /**
     * 查询商品
     * @param cid 商品分类id
     * @param first 查询对应的起始值
     * @param max 一页展示多少数据
     * @return 商品对象集合
     */
    List<?> getList(int cid, int first, int max);

    /**
     * 删除一条数据
     * @param product 删除对象
     */

}
