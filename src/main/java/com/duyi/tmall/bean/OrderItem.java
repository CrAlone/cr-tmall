package com.duyi.tmall.bean;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 订单商品表
 */
@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItem implements Serializable {
    /**
     * 订单商品表
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 订单id
     */
    @ManyToOne
    @JoinColumn(name = "oid")
    private OrderTable orderTable;

    /**
     * 商品id
     */
    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;
    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 商品总价格
     */
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    /**
     * 是否删除
     */
    private Integer del;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    @Transient
    private Image coverImage;
    public Image getCoverImage(){
        if (!ObjectUtil.isEmpty(product)){
            List<Image> imageList = product.getImages();
            for (Image image : imageList){
                if (image.getType() == Image.Type.COVER){
                    return image;
                }
            }
        }
        return new Image();
    }
}
