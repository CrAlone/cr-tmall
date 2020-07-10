package com.duyi.tmall.bean;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 商品表
 */
@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {
    /**
     * 商品id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 商品分类id 商品分类的对象
     */
    @ManyToOne()
    @JoinColumn(name = "cid")
    private Category category;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品简介
     */
    @Column(name = "sub_title")
    private String subTitle;
    /**
     * 原价
     */
    @Column(name = "o_price")
    private BigDecimal oPrice;
    /**
     * 现价
     */
    @Column(name = "n_price")
    private BigDecimal nPrice;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 商品图片id
     */
    @OneToMany
    @JoinColumn(name = "imageId")
    @Where(clause = "del != 1")
    private List<Image> images;
    /**
     * 评论总数
     */
    @Column(name = "comment_count")
    private String commentCount;
    /**
     * 商品是否上架
     */
    private Integer added;
    /**
     * 销量
     */
    @Column(name = "sale_count")
    private Integer saleCount;
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
