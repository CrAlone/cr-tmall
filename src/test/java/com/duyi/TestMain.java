package java.com.duyi;


import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;

import com.duyi.tmall.bean.Category;
import com.duyi.tmall.bean.Image;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.bean.User;
import com.duyi.tmall.dao.base.BaseCategoryDao;
import com.duyi.tmall.dao.base.BaseProductDao;
import com.duyi.tmall.dao.base.BaseUserDao;


import com.duyi.tmall.service.base.BaseImageService;
import com.duyi.tmall.service.base.BaseProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
public class TestMain {
    @Autowired
    private BaseUserDao userDao;
    @Autowired
    private BaseCategoryDao categoryDao;
    @Autowired
    private BaseProductDao productDao;
    @Autowired
    private BaseProductService productService;
    @Autowired
    private BaseImageService imageService;

    @Test
    public void t1(){
        User user = new User();
        user.setName("独自2");
        user.setEmail("cr__alone@163.com");
        user.setPassword(DigestUtil.md5Hex("123456"));
        user.setGro(User.Gro.MANAGE);
        user.setDel(0);
        user.setCreateTime(DateUtil.date());
        int insert = userDao.insert(user);
        System.out.println(insert);
    }
    @Test
    public void t2(){
        for (int i = 0; i < 1000; i++){
            Category category = new Category();
            category.setName("电脑"+i);
            category.setRecommend(i);
            category.setDel(0);
            category.setCreateTime(DateUtil.date());
            Integer insert = categoryDao.insert(category);
            System.out.println(insert);
        }
    }
    @Test
    public void t3(){
        Object query = categoryDao.query(1);

        categoryDao.del(query);
    }
    @Test
    public void t4(){
        Category category= (Category) categoryDao.query(1);
        category.setRecommend(50);
        categoryDao.update(category);
    }
    @Test
    public void t5(){
       for (int i =0;i<100;i++){
           Product product = new Product();
           Category category = new Category();
           category.setId(7);
           product.setCreateTime(DateUtil.date());
           product.setCategory(category);
           product.setName("苹果电脑"+i);
           product.setSubTitle("好用不贵");
           product.setOPrice(new BigDecimal(1000+i));
           product.setNPrice(new BigDecimal(500+i));
           product.setStock(i);
           product.setSaleCount(200);
           product.setDel(0);
           productDao.insert(product);
       }
    }
    @Test
    public void t6(){
        Integer count = productDao.count();
        System.out.println(count);
    }
    @Test
    public void t7(){
        List<Image> query = imageService.query(6);
        System.out.println(query.size());
    }


}
