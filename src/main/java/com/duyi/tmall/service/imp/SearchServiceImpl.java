package com.duyi.tmall.service.imp;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.dao.base.BaseProductDao;
import com.duyi.tmall.service.base.BaseSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 模糊查询
 */
@Service
public class SearchServiceImpl implements BaseSearchService {
    @Autowired
    private BaseProductDao productDao;
    public List<Product> likeQuery(String keyword, String sort,int cid) {
        //若果等于空那么只有模糊查询 并没有升序排序
        if (StrUtil.isEmpty(sort)){
            return productDao.likeQuery(keyword);
        }
        //创建一个长度为2的数组 准备存储模糊查询的关键字和是升序还是降序
        String[] sorts = new String[2];
        //如果传过来的sort不包含 Inverse 则默认升序
        if (!StrUtil.contains(sort,"Inverse")){
            sorts[1] = "asc";
        }
        if (StrUtil.contains(sort,"commentCount")){
            sorts[0] = "commentCount";
        }
        if (StrUtil.contains(sort,"createTime")){
            sorts[0] = "createTime";
        }
        if (StrUtil.contains(sort,"saleCount")){
            sorts[0] = "saleCount";
        }
        if (StrUtil.contains(sort,"nPrice")){
            sorts[0] = "nPrice";
        }
        if (ObjectUtil.isEmpty(cid)){
            cid = -1;
        }
        //判断是按照
        return productDao.likeQuery(keyword,sorts,cid);
    }
}
