package com.duyi.tmall.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 页码的信息
 */
@Getter
@Setter
public class Pagination<T> {
    /**
     * 设置一个页面展示10条数据
     */
    public static final int PAGE_SIZE = 10;
    /**
     * 用于存储当前页面的所有数据的集合
     */
    private List<?> data;
    /**
     * 起始页数
     */
    private int start;
    /**
     * 末尾页数
     */
    private int end;
    /**
     * 前端点击的页数
     */
    private int pageNum;
    /**
     * 展示从几开始展示的索引
     */
    private int first;
    /**
     * 显示多少数据
     */
    private int max;
    /**
     * 获取数据总页数
     */
    private int pageTotal;
    /**
     * 对象数据的数量
     */
    private int num;
    /**
     * 上一页
     */
    private boolean hasPrevious;
    /**
     * 下一页
     */
    private boolean hasNext;
    /**
     * 一共多少数据
     */
    private int count;
    public Pagination(int pageNum){
        this.pageNum = pageNum;
    }
    public Pagination(){
        pageNum = 1;
    }

    /**
     * 初始化 只能让Pagination创建一次
     * @param <T> 造型
     * @return 返回一个Pagination对象
     */
    public static <T>Pagination<T> getPagination(int count,int pageNum){
        Pagination<T> pagination = new Pagination<T>(pageNum);
        pagination.setCount(count);
        pagination.setPageTotal(count % Pagination.PAGE_SIZE == 0? count / Pagination.PAGE_SIZE :count /Pagination.PAGE_SIZE +1);
        pagination.setFirst(getFirstIndex(pagination.getPageNum(),pagination.getPageTotal()));
        pagination.setMax(Pagination.PAGE_SIZE);
        pagination.handlerPage();
        return pagination;
    }
    /**
     * 应为数据库索引为0 传入过来的是1 那么想要数据库从 显示中的1开始 则需要计算 得到对应的页数 并返回数据库中从第几个数据展示出来
     * @param pageNum 传入的页数
     * @return 返回的是从数据库中的第几个开始展示
     */
    public static int getFirstIndex(int pageNum,int pageTotal){
        if (pageNum <= 0){
            return 0;
        }
        if (pageNum >= pageTotal){
            pageNum = pageTotal;
        }
        return (pageNum-1)*PAGE_SIZE;
    }
    public boolean getHasPrevious(){
        return pageNum > 1;
    }
    public boolean getHasNext(){
        return pageNum < pageTotal;
    }
    public void setPageNum(){
        if (pageNum < 1){
            pageNum = 1;
        }
        if (pageNum > pageTotal){
            pageNum = pageTotal;
        }
    }

    public void handlerPage(){

        if (pageTotal < 10){
            start = 1;
            end = pageTotal;
        }else {
            //获取总页数减去 点中的页数 如 12 - 9 == 3
            int index = pageTotal - pageNum;
            //根据剩余页判断是否小于4
            if (index < 4){
                //如果满足 证明剩余的页数 不满足设计样式 左边5个页数 右边4个也是
                //1 2 3 4 5 6 7 8 9 10第一页
                //11 12 第二页
                //则直接将第二页的初始页设为11 末尾12
                start = pageTotal -9 ;
                end = pageTotal;
                //如果满足 则判断当前第一页点击的页数 离第一个页数的距离 如 4 则不满足
            }else if (pageNum -5 <= 0){
                //如果不满足 则不变
                start = 1 ;
                end = 10 ;
            }else{
                //满足
                start = pageNum -5;
                end = pageNum +4;
            }
        }
    }
}
