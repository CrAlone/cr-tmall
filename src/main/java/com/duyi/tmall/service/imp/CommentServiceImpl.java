package com.duyi.tmall.service.imp;

import com.duyi.tmall.bean.Comment;
import com.duyi.tmall.dao.base.BaseCommentDao;
import com.duyi.tmall.service.base.BaseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 商品评论数据
 */
@Service
public class CommentServiceImpl implements BaseCommentService {
    @Autowired
    private BaseCommentDao commentDao;
    public void add(Comment comment) {
        commentDao.insert(comment);
    }

    public List<Comment> list(int id) {
        return commentDao.list(id);
    }
}
