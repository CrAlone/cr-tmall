package com.duyi.tmall.bean;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 商品分类表
 */
@Entity
@Table(name = "category")
@Getter
@Setter
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
    @OneToMany
    @JoinColumn(name = "cid")
    @Where(clause = "del != 1")
    private List<Product> products;
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
