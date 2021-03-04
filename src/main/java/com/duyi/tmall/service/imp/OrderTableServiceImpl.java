package com.duyi.tmall.service.imp;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.duyi.tmall.bean.*;
import com.duyi.tmall.dao.base.BaseOrderTableDao;
import com.duyi.tmall.dao.base.BaseProductDao;
import com.duyi.tmall.service.base.BaseOrderItemService;
import com.duyi.tmall.service.base.BaseOrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 订单表service
 */
@Service
public class OrderTableServiceImpl implements BaseOrderTableService {
    @Autowired
    private BaseOrderTableDao orderTableDao;
    @Autowired
    private BaseOrderItemService itemService;
    @Autowired
    private BaseProductDao productDao;
    public void add(OrderTable orderTable) {
        orderTableDao.insert(orderTable);
    }
    public Pagination getList(int pageNum) {
        int count = orderTableDao.count();
        Pagination<OrderTable> pagination = Pagination.getPagination(count,pageNum);
        List<OrderTable> list = (List<OrderTable>) orderTableDao.getList(pagination.getFirst(),pagination.getMax());
        orderItemCount(list);
        pagination.setData(list);
        pagination.setNum(count);
        return pagination;
    }

    public void totalPrice(List<OrderItem> items) {

    }

    public void createOrder(List<Cart> list, OrderTable orderTable) {
        //给前端传输过来的表单 有些属性赋值
        orderTable.setDel(0);
        //创建时间
        orderTable.setCreateTime(DateUtil.date());
        //未支付
        orderTable.setStatus(OrderTable.Status.UNPAID);
        //定义一个订单编号
        Snowflake snowflake = IdUtil.createSnowflake(1,1);
        orderTable.setOrderCode(snowflake.nextIdStr());
        //订单表的总价 订单表中的商品订单总价累计叠加后的价格
        BigDecimal orderPrice = new BigDecimal(0);
        //创建一个商品订单集合
        List<OrderItem> items = new ArrayList<OrderItem>();

        for (Cart cart : list){
            OrderItem orderItem = new OrderItem();
            if (product(cart.getProduct(),cart.getNumber())){
                //商品价格
                //一个购物车 对应你一个商品订单  一个订单有多个商品订单
                orderItem.setDel(0);
                //创建时间
                orderItem.setCreateTime(DateUtil.date());
                //商品id
                orderItem.setProduct(cart.getProduct());
                //数量
                orderItem.setCount(cart.getNumber());
                //商品价格
                BigDecimal price = cart.getProduct().getNPrice();
                //总价
                orderItem.setTotalPrice(price.multiply(new BigDecimal(cart.getNumber())));
                //将商品订单存入集合中
                orderItem.setOrderTable(orderTable);
                items.add(orderItem);

                //订单总价 不断叠加集合中的多有商品订单的价格
                orderPrice = orderPrice.add(orderItem.getTotalPrice());
            }
        }

        orderTable.setOrderItemList(items);
        orderTable.setOrderPrice(orderPrice);
        //数据库生成订单
        orderTableDao.insert(orderTable);
        System.out.println(orderTable.getOrderItemList().size());
    }

    public OrderTable getOrderTable(int oid) {

        OrderTable orderTable = orderTableDao.getOrderTable(oid);
        //付款时间
        orderTable.setPayTime(DateUtil.date());
        //更改为已支付
        orderTable.setStatus(OrderTable.Status.PENDING);
        orderTableDao.update(orderTable);
        return orderTable;
    }

    public OrderTable query(int oid) {
        return orderTableDao.getOrderTable(oid);
    }

    public List<OrderTable> list(int uid) {
        return orderTableDao.list(uid);
    }

    public void update(int oid) {
        OrderTable orderTable = orderTableDao.getOrderTable(oid);
        //点击发货 更改 确认收货
        orderTable.setStatus(OrderTable.Status.SHIPPED);
        orderTable.setDeliveryTime(DateUtil.date());
        orderTableDao.update(orderTable);
    }
    public void affirm(int oid) {
        OrderTable orderTable = orderTableDao.getOrderTable(oid);
        //点击发货 更改 待评价
        orderTable.setStatus(OrderTable.Status.ACCOMPLISH);
        orderTableDao.update(orderTable);
    }
    /**
     * 获取每个订单表的商品数量 并存入对象中
     * @param tables 订单表集合
     *
     */
    private void orderItemCount(List<OrderTable> tables){
        for (OrderTable table : tables){
            List<OrderItem> items = table.getOrderItemList();
            if (items != null){
                table.setOrderItemCount(itemService.orderItemCount(items));
                BigDecimal price = new BigDecimal("0.0");
            for (OrderItem ite : items){
                if (ite != null){
                    price = price.add(ite.getTotalPrice());
                }
            }
            table.setOrderPrice(price);
            }else {
                table.setOrderItemCount(0);
            }
        }
    }

    /**
     * 判断库存中的商品 和实际中购买商品的数量 是否超过库存 若超过  则失败 若不超过 减去购买的数量并继续执行
     * @param product 商品对象
     * @param count 商品数量
     * @return false 不执行 true 执行
     */
    private boolean product(Product product,int count){
        if (product.getStock() - count >= 0){
            //库存减少count个
            product.setStock(product.getStock() - count);
            //销量增加count个
            product.setSaleCount(product.getSaleCount() + count);
            productDao.update(product);
            return true;
        }
        return false;
    }
}
