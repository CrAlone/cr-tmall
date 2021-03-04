package com.duyi.tmall.service.base;

import com.duyi.tmall.bean.Comment;

import java.util.List;

/**
 * @author 评论数据
 */
public interface BaseCommentService {
    /**
     * 添加评论数据
     * @param comment 评论对象
     */
    void add(Comment comment);

    /**
     * 根据商品id查询 该商品的所有评论
     * @param id 商品id
     * @return 评论集合
     */
    List<Comment> list(int id);
}
