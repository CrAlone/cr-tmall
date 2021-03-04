package com.duyi.tmall.dao.imp;

import com.duyi.tmall.bean.Cart;
import com.duyi.tmall.bean.Comment;
import com.duyi.tmall.dao.base.BaseCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author 购物车dao
 */
@Repository
public class CartDaoImp extends BeanDaoImpl<Cart> implements BaseCartDao {
    @Autowired
    private BaseCartDao cartDao;
    public List<Cart> list(int uid) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Cart> query = builder.createQuery(Cart.class);
        Root<Cart> root = query.from(Cart.class);
        query.where(builder.and(builder.equal(root.get("del"),0),builder.equal(root.get("user"),uid)));
        return getSession().createQuery(query).getResultList();
    }
}
