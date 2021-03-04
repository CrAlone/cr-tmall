package com.duyi.tmall.dao.imp;

import com.duyi.tmall.bean.Comment;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.dao.base.BaseCommentDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author 商品评论表
 */
@Repository
public class CommentDaoImp extends BeanDaoImpl<Comment> implements BaseCommentDao {


    public List<Comment> list(int pid) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Comment> query = builder.createQuery(Comment.class);
        Root<Comment> root = query.from(Comment.class);
        query.where(builder.and(builder.equal(root.get("del"),0),builder.equal(root.get("product"),pid)));
        return getSession().createQuery(query).getResultList();
    }
}
