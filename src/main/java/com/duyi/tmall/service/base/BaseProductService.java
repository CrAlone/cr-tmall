package com.duyi.tmall.service.base;


import com.duyi.tmall.bean.Pagination;
import com.duyi.tmall.bean.Product;


/**
 * @author 商品service
 */
public interface BaseProductService {
    /**
     * 根据商品分类的id  查询对应所有的商品
     * @param cid 商品分类id
     * @param pageNum 传递的页数
     * @return 返回对应的商品集合
     */
    Pagination getList(int cid, int pageNum);

    /**
     * 删除一条数据
     * @param product 删除对象
     */
    void remove(Product product);

    /**
     * 根据id查询对应的商品
     * @param id
     * @return
     */
    Product query(int id);

    /**
     * 修改一条数据
     * @param product 数据对象
     */
    void update(Product product);

    /**
     * 插入一条信息
     * @param product 插入的对象
     */
    void add(Product product);

}
