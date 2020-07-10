package com.duyi.tmall.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 购物车
 */
@Entity
@Table(name = "cart")
@Setter
@Getter
public class Cart implements Serializable {
    /**
     * 购物车id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户id
     */
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    /**
     * 商品id
     */
    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;
    /**
     * 商品数量
     */
    private Integer number;
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
