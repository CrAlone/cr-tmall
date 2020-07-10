package com.duyi.tmall.dao.imp;

import cn.hutool.core.util.ReflectUtil;
import com.duyi.tmall.dao.base.BaseBeanDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;



import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author 实现通用方法 增删改查
 */
public class BeanDaoImp<T>   implements BaseBeanDao {
    /**
     * 创建session工厂
     */
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * 传入进来的类对象
     */
    protected Class clazz;
    public BeanDaoImp() {
        ParameterizedType pt = (ParameterizedType) (getClass().getGenericSuperclass());
        if (pt != null) {
            //获取类
            clazz = (Class) pt.getActualTypeArguments()[0];
        }
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }

    public Integer insert(Object obj) {
        Serializable save = getSession().save(obj);
        return (Integer) save;
    }

    public Object query(Integer id) {
        return getSession().get(clazz,id);
    }

    public Integer count() {
        List<?> list = this.findAll();
        if (list ==null || list.size() ==0){
            return 0;
        }
        return list.size();
    }

    public void update(Object obj) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.update(session.merge(obj));
        transaction.commit();
    }

    public void del(Object obj) {
        Field field = ReflectUtil.getField(clazz,"del");
        try {
            field.setAccessible(true);
            field.set(obj,1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(obj);
        this.update(obj);
    }

    public List<?> findAll() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(clazz);
        Root root = query.from(clazz);
        query.where(builder.equal(root.get("del"),0));
        return getSession().createQuery(query).getResultList();
    }

    public List<?> getList(int first, int max) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(clazz);
        Root root = query.from(clazz);
        query.where(builder.equal(root.get("del"),0));
        //查询所有 根据页面返回 limit first,max
        //从first个开始 到max个
        return getSession().createQuery(query).setFirstResult(first)
                .setMaxResults(max)
                .getResultList();
    }
}
