package com.duyi.tmall.service.base;

import com.duyi.tmall.bean.Image;

import java.util.List;

/**
 * @author 商品图片service
 */
public interface BaseImageService {
    /**
     * 添加图片
     * @param image 图片信息
     */
    void add(Image image);

    /**
     * 根据商品id查询对象的所有图片
     * @param id 商品id
     * @return 放回该商品的所有id
     */
    List<Image> query(int id);
}
