package com.duyi.tmall.dao.base;



import com.duyi.tmall.bean.Comment;

import java.util.List;

/**
 * @author 商品评论表 dao
 */
public interface BaseCommentDao extends BaseBeanDao{
    /**
     * 根据商品id查询 该商品的所有评论
     * @param pid 商品id
     * @return 评论集合
     */
    List<Comment> list(int pid);

}
