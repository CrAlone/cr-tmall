package com.duyi.tmall.service.base;


import com.duyi.tmall.bean.Category;
import com.duyi.tmall.bean.Pagination;



/**
 * @author 商品类型service
 */
public interface BaseCategoryService {
    /**
     * 根据第几页查询 当前页面的所有数据
     * @param pageNum 页数
     * @return 返回当前页数的所有数据
     */
    Pagination getList(int pageNum);

    /**
     * 根据id删除显示
     * @param category 商品类型id
     */
    void delete(Category category);

    /**
     * 修改对象
     * @param category 对象
     */
    void alter(Category category);

    /**
     * 根据id查询一个对象
     * @param id 对象id
     * @return 返回对应的对象
     */
    Category query(int id);

    /**
     * 添加一行数据
     * @param category
     */
    void add(Category category);
}
