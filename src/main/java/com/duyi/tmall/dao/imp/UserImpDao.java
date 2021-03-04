package com.duyi.tmall.dao.imp;

import com.duyi.tmall.bean.User;

import com.duyi.tmall.dao.base.BaseUserDao;


import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 * @author 实现用户dao的方法
 */
@Repository
public class UserImpDao extends BeanDaoImpl<User> implements BaseUserDao {

    public User get(String name) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("name"),name));
        try {
            return  session.createQuery(query).getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }

    public User sendEmail(String email) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.where(builder.equal(root.get("email"),email));
        try {
            return  session.createQuery(query).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
