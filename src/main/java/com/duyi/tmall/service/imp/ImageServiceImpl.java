package com.duyi.tmall.service.imp;

import com.duyi.tmall.bean.Image;
import com.duyi.tmall.dao.base.BaseImageDao;
import com.duyi.tmall.service.base.BaseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author 图片service
 */
@Service
public class ImageServiceImpl implements BaseImageService {
    @Autowired
    private BaseImageDao imageDao;
    public void add(Image image) {
        imageDao.insert(image);
    }


    public void delete(Image image) {
        imageDao.del(image);
    }

    public Image get(int id) {
        return (Image) imageDao.query(id);
    }
}
