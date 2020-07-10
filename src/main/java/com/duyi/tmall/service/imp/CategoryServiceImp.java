package com.duyi.tmall.service.imp;


import cn.hutool.core.date.DateUtil;
import com.duyi.tmall.bean.Category;
import com.duyi.tmall.bean.Pagination;
import com.duyi.tmall.dao.base.BaseCategoryDao;
import com.duyi.tmall.service.base.BaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 商品分类servce
 */
@Service
public class CategoryServiceImp implements BaseCategoryService {
    @Autowired
    private BaseCategoryDao categoryDao;
    public Pagination getList(int pageNum) {
        //获取数据总数
        int count = categoryDao.count();
        Pagination<Category> pagination = Pagination.getPagination(count,pageNum);
        List<Category> list = (List<Category>) categoryDao.getList(pagination.getFirst(),pagination.getMax());
        int num = list.size();
        pagination.setNum(num);
        pagination.setData(list);
        return pagination;
    }
    public void delete(Category category) {
        Object query = categoryDao.query(category.getId());
        categoryDao.del(query);
    }

    public void alter(Category category) {
       Category c = this.query(category.getId());
       c.setRecommend(category.getRecommend());
       c.setName(category.getName());
        categoryDao.update(c);
    }
    public Category query(int id) {
        return (Category) categoryDao.query(id);
    }

    public void add(Category category) {
        category.setCreateTime(DateUtil.date());
        category.setDel(0);
        categoryDao.insert(category);
    }

}
