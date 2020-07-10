package com.duyi.tmall.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 订单表
 */
@Entity
@Table(name = "order_table")
@Getter
@Setter
public class OrderTable implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;
    /**
     * 用户id
     */
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 收货人
     */
    private String receivers;
    /**
     * 编码
     */
    private String postcode;
    /**
     * 留言
     */
    private String message;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 付款时间
     */
    @Column(name = "pay_time")
    private Date payTime;
    /**
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;
    /**
     * 订单状态
     */
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private Status status;
    /**
     * 是否删除
     */
    private Integer del;
    /**
     * 订单价格
     */
    @Column(name = "order_price")
    private BigDecimal orderPrice;

    /**
     * 确认时间
     */
    @Column(name = "confirm_time")
    private Date confirmTime;
    public enum Status{
        SENT,UNSENT,ARRIVE;
    }

}
