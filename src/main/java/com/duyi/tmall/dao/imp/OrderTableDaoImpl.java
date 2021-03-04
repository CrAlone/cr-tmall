package com.duyi.tmall.dao.imp;



import com.duyi.tmall.bean.OrderTable;
import com.duyi.tmall.dao.base.BaseOrderTableDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author 订单表dao
 */
@Repository
public class OrderTableDaoImpl extends BeanDaoImpl<com.duyi.tmall.bean.OrderTable> implements BaseOrderTableDao {

    public OrderTable getOrderTable(int oid) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<OrderTable> query = builder.createQuery(OrderTable.class);
        Root<OrderTable> root = query.from(OrderTable.class);
        query.where(builder.and(builder.equal(root.get("del"),0),builder.equal(root.get("id"),oid)));
        return getSession().createQuery(query).getSingleResult();
    }

    public List<OrderTable> list(int uid) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<OrderTable> query = builder.createQuery(OrderTable.class);
        Root<OrderTable> root = query.from(OrderTable.class);
        query.where(builder.and(builder.equal(root.get("del"),0),builder.equal(root.get("user"),uid)));
        return getSession().createQuery(query).getResultList();
    }
}
