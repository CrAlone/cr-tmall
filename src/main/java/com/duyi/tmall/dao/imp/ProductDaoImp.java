package com.duyi.tmall.dao.imp;

import com.duyi.tmall.bean.Product;
import com.duyi.tmall.dao.base.BaseProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author 商品信息表 dao
 */
@Repository
public class ProductDaoImp extends BeanDaoImp<Product> implements BaseProductDao {
    @Autowired
    private BaseProductDao baseProductDao;
    public List<?> getList(int cid, int first, int max) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.where(builder.and(builder.equal(root.get("del"),0),builder.equal(root.get("category"),cid)));
        return getSession().createQuery(query).setFirstResult(first)
                .setMaxResults(max).getResultList();
    }
}
