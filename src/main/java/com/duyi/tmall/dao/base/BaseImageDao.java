package com.duyi.tmall.dao.base;

import com.duyi.tmall.bean.Image;

import java.util.List;

/**
 * @author 商品图片dao
 */
public interface BaseImageDao extends BaseBeanDao{
    /**
     * 根据商品id查询所有的图片
     * @param id 商品id
     * @return 返回图片集合
     */
    List<Image> list(int id);
}
