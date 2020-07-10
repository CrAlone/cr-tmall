package com.duyi.tmall.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author 商品图片
 */
@Entity
@Table(name = "image")
@Getter
@Setter
public class Image {
    /**
     * 图片id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 图片名称
     */
    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;
    /**
     * 图片url地址
     */
    @Column(name = "url_image")
    private String urlImage;
    /**
     * 图片详细类容
     */
    private String content;
    /**
     * 删除标记
     */
    private Integer del;
    @Enumerated(EnumType.STRING)
    private Type type;
    public enum Type{
        TOP,DETAIL,COVER
    }
}
