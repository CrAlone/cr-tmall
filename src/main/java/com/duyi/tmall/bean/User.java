package com.duyi.tmall.bean;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 用户表单
 */
@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    public User(){
        email = "";
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;
    @Transient
    private int verify;
    /**
     * 用户组
     */
    @Enumerated(EnumType.STRING)
    private Gro gro;
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
     * 两种身份 MANAGE管理员
     * ORDINARY 普通成员
     */
    public enum Gro {
        //两种身份 MANAGE管理员
        //ORDINARY 普通成员
        MANAGE,ORDINARY
    }
}

