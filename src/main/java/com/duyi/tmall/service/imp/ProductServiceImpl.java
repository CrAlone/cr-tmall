package com.duyi.tmall.service.imp;


import cn.hutool.core.date.DateUtil;
import com.duyi.tmall.bean.Comment;
import com.duyi.tmall.bean.Pagination;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.dao.base.BaseProductDao;
import com.duyi.tmall.service.base.BaseCommentService;
import com.duyi.tmall.service.base.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author 商品service
 */
@Service
public class ProductServiceImpl implements BaseProductService {
    @Autowired
    private BaseProductDao productDao;
    @Autowired
    BaseCommentService commentService;
    public Pagination getList(int cid, int pageNum) {
        int count = productDao.count();
        Pagination<Product> pagination = Pagination.getPagination(count, pageNum);
        List<Product> list = (List<Product>) productDao.getList(cid,pagination.getFirst(),pagination.getMax());
        int num = list.size();
        pagination.setNum(num);
        pagination.setData(list);
        return pagination;
    }
    public void remove(Product product) {
        Object query = productDao.query(product.getId());
        productDao.del(query);
    }

    public Product query(int id) {

        return (Product) productDao.query(id);
    }

    public void update(Product product) {
        Product product1 = (Product) productDao.query(product.getId());
        product.setCreateTime(product1.getCreateTime());
        product.setDel(0);
        productDao.update(product);
    }

    public void add(Product product) {
        product.setDel(0);
        product.setCreateTime(DateUtil.date());
        productDao.insert(product);
    }

}
