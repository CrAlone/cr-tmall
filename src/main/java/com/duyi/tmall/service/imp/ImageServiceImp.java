package com.duyi.tmall.service.imp;

import com.duyi.tmall.bean.Image;
import com.duyi.tmall.dao.base.BaseImageDao;
import com.duyi.tmall.service.base.BaseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 图片service
 */
@Service
public class ImageServiceImp implements BaseImageService {
    @Autowired
    private BaseImageDao imageDao;
    public void add(Image image) {
        imageDao.insert(image);
    }

    public List<Image> query(int id) {
        return imageDao.list(id);
    }
}
