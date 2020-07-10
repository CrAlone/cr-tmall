package com.duyi.tmall.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 商品评论表
 */
@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment implements Serializable {
    /**
     * 商品评论表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 商品id
     */
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;
    /**
     * 用户id
     */
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 是否删除
     */
    private Integer del;
}
