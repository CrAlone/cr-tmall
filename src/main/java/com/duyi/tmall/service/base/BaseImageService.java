package com.duyi.tmall.service.base;

import com.duyi.tmall.bean.Image;



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
     * 删除一张图片
     * @param image 对象
     */
    void delete(Image image);

    /**
     * 根据图片id查询图片的内容
     * @param id 图片id
     * @return 返回图片信息
     */
    Image get(int id);

}
