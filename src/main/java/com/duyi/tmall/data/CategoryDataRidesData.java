package com.duyi.tmall.data;
import com.duyi.tmall.bean.Category;
import com.duyi.tmall.dao.base.BaseCategoryDao;
import java.util.List;

/**
 * @author redies
 */
public class CategoryDataRidesData {
    private BaseCategoryDao categoryDao;
    public List<Category> getList(int start,int end){
        return (List<Category>) categoryDao.getList(start,end);
    }
}
