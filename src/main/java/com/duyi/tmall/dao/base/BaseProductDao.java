package com.duyi.tmall.dao.base;



import com.duyi.tmall.bean.Product;

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
     * 根据条件搜索相关的商品
     * @param keyword 某些商品的信息
     * @return 返回所有相关的商品信息
     */
    List<Product> likeQuery(String keyword);

    /**
     * 根据条件搜索相关的商品 并排序
     * @param keyword 相关商品条件
     * @param sort 升序 and 排序
     * @param cid 商品分类id
     * @return 返回所有相关的商品信息
     */
    List<Product> likeQuery(String keyword,String[] sort,int cid);


}
