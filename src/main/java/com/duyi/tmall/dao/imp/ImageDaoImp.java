package com.duyi.tmall.dao.imp;

import com.duyi.tmall.bean.Image;
import com.duyi.tmall.dao.base.BaseImageDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author 商品图片dao
 */
@Repository
public class ImageDaoImp extends BeanDaoImp<Image> implements BaseImageDao {

    public List<Image> list(int id) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Image> query = builder.createQuery(Image.class);
        Root<Image> root = query.from(Image.class);
        query.where(builder.equal(root.get("product"),id));
        return getSession().createQuery(query).getResultList();
    }
}
