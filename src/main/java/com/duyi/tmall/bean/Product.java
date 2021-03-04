package com.duyi.tmall.bean;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
     * 当前商品的评价类容
     */
    @OneToMany
    @JoinColumn(name = "pid")
    @Where(clause = "del != 1")
    @Transient
    private List<Comment> comments = new ArrayList<Comment>();
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
    @JoinColumn(name = "pid")
    @Where(clause = "del != 1")
    private List<Image> images;
    /**
     * 评论总数
     */
    @Column(name = "comment_count")
    private Integer commentCount;
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
    /**
     * 封面图片
     */
    @Transient
    private List<Image> coverList = new ArrayList<Image>();
    public List<Image> getCoverList(){
        //先清空在存储
        if (coverList != null){
            coverList.clear();
        }
        imageClassify();
        return coverList;
    }
    /**
     * 顶部图片
     */
    @Transient
    private List<Image> topList = new ArrayList<Image>();
    public List<Image> getTopList(){
        //先清空在存储
        if (topList != null){
            topList.clear();
        }
        imageClassify();
        return topList;
    }
    /**
     * 商品详情图片
     */
    @Transient
    private List<Image> detailList = new ArrayList<Image>();
    public List<Image> getDetailList(){
        //先清空在存储
        if (detailList != null){
            detailList.clear();
        }
        imageClassify();
        return detailList;
    }

    /**
     * 将图片进行分类
     */
    public void imageClassify(){
        if (!ObjectUtil.isAllEmpty(images)){
            for (Image image :images){
                //如果类型是封面图片 则存入封面图片集合中
                if (Image.Type.COVER == image.getType()){
                    coverList.add(image);
                }else if (Image.Type.TOP == image.getType()){
                    topList.add(image);
                }else {
                    detailList.add(image);
                }
            }
        }
    }
}
