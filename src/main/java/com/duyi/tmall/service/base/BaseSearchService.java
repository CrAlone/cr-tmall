package com.duyi.tmall.service.base;

import com.duyi.tmall.bean.Product;

import java.util.List;

/**
 * @author 按条件查询
 */
public interface BaseSearchService {
    /**
     * 根据条件搜索相关的商品 并排序
     * @param keyword 相关商品条件
     * @param sort 升序 and 排序
     * @param cid 商品分类id
     * @return 返回所有相关的商品信息
     */
    List<Product> likeQuery(String keyword , String sort,int cid);
}
