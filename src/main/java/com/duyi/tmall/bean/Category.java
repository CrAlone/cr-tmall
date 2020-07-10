package com.duyi.tmall.bean;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 商品分类表
 */
@Entity
@Table(name = "category")
@Data
public class Category implements Serializable {
    /**
     * 分类id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 该类商品的推荐值
     */
    private Integer recommend;
    /**
     * 是否删除
     */
    private Integer del;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
