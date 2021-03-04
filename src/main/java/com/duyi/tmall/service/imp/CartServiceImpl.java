package com.duyi.tmall.service.imp;

import cn.hutool.core.date.DateUtil;
import com.duyi.tmall.bean.*;
import com.duyi.tmall.dao.base.BaseCartDao;
import com.duyi.tmall.service.base.BaseCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements BaseCartService {
    @Autowired
    private BaseCartDao cartDao;

    public boolean joinCart(Product product, int num, User user) {
        if ((product.getStock() - num) > 0){
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setCreateTime(DateUtil.date());
            cart.setProduct(product);
            cart.setDel(0);
            cart.setNumber(num);
            cartDao.insert(cart);
        }
        return (product.getStock() - num) >= 0;
    }
    public List<Cart> getOrderItem(int uid) {
        return cartDao.list(uid);
    }

    public void del(int cid) {
        Cart cart = (Cart) cartDao.query(cid);
        cartDao.del(cart);
    }
}
