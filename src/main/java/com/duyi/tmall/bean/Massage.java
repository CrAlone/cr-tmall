package com.duyi.tmall.bean;


import lombok.Data;

/**
 * @author 用于响应给浏览器信息的类
 */
@Data
public class Massage {
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private Object data;
    /**
     * 响应码
     */
    private int code;
}
