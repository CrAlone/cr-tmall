package com.duyi.tmall.service.imp;


import com.duyi.tmall.bean.OrderItem;
import com.duyi.tmall.dao.base.BaseOrderItemDao;
import com.duyi.tmall.service.base.BaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 订单商品表
 */
@Service
public class OrderItemServiceImpl implements BaseOrderItemService {
    @Autowired
    private BaseOrderItemDao itemDao;
    public void add(OrderItem orderItem) {
        itemDao.insert(orderItem);
    }

    public List<OrderItem> query(int oid) {
        List<OrderItem> items = (List<OrderItem>) itemDao.query(oid);
        return (List<OrderItem>) itemDao.query(oid);

    }
    public BigDecimal totalPrice(int count, BigDecimal price) {
        BigDecimal bigDecimal = new BigDecimal(count);

        return bigDecimal.multiply(price);
    }
    public int orderItemCount(List<OrderItem> orderItems){
        int i = 0;
        for (OrderItem item : orderItems){
            i += item.getCount();
        }
        return i;
    }

    public OrderItem get(int id) {
        return (OrderItem) itemDao.query(id);
    }
}
