package com.duyi.tmall.dao.imp;

import cn.hutool.core.util.StrUtil;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.dao.base.BaseProductDao;

import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 商品信息表 dao
 */
@Repository
public class ProductDaoImp extends BeanDaoImpl<Product> implements BaseProductDao {
    public List<?> getList(int cid, int first, int max) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.where(builder.and(builder.equal(root.get("del"),0),builder.equal(root.get("category"),cid)));
        return getSession().createQuery(query).setFirstResult(first)
                .setMaxResults(max).getResultList();
    }
    public List<Product> likeQuery(String keyword) {
       return likeQuery(keyword,new String[]{},-1);
    }
    public List<Product> likeQuery(String keyword, String[] sort,int cid) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        if (cid == -1){
            query.where(builder.and(builder.equal(root.get("del"),0),builder.like(root.<String>get("name"),"%" + keyword + "%")));
        }
        else {
            query.where(builder.and(builder.equal(root.get("del"),0),builder.equal(root.get("category"),cid),builder.like(root.<String>get("name"),"%" + keyword + "%")));
        }
        //如果数组不为null
        if (!StrUtil.isAllEmpty(sort)){
           List<Order> orders = new LinkedList<Order>();
           //如果是升序 则升序查询
           if ("asc".equalsIgnoreCase(sort[1])){
               orders.add(builder.asc(root.get(sort[0])));
           }else {
               orders.add(builder.desc(root.get(sort[0])));
           }
           query.orderBy(orders);
        }
        return getSession().createQuery(query).getResultList();
    }
}
