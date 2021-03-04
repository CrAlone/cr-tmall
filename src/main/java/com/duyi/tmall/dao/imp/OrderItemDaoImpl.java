package com.duyi.tmall.dao.imp;

import com.duyi.tmall.bean.OrderItem;
import com.duyi.tmall.dao.base.BaseOrderItemDao;

import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author 商品订单表 dao
 */
@Repository
public class OrderItemDaoImpl extends BeanDaoImpl<OrderItem> implements BaseOrderItemDao {
    public List<OrderItemDaoImpl> getList(int id) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<OrderItemDaoImpl> query = builder.createQuery(OrderItemDaoImpl.class);
        Root<OrderItemDaoImpl> root = query.from(OrderItemDaoImpl.class);
        query.where(builder.and(builder.equal(root.get("del"),0),builder.equal(root.get("orderTable"),id)));
        return getSession().createQuery(query).getResultList();
    }

}
