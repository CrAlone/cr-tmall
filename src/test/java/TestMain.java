
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;

import com.duyi.tmall.bean.*;
import com.duyi.tmall.dao.base.BaseCategoryDao;
import com.duyi.tmall.dao.base.BaseProductDao;
import com.duyi.tmall.dao.base.BaseUserDao;


import com.duyi.tmall.service.base.*;
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
        @Autowired
        private BaseUserService userService;
        @Autowired
        private BaseOrderTableService orderTableService;
        @Autowired
        private BaseOrderItemService itemService;
        @Autowired
        private BaseCommentService commentService;
        @Autowired
        private BaseSearchService searchService;
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
            Product product = productService.query(6);
            System.out.println(product.getImages().size());
        }
        @Test
        public void t8(){
            for (int i = 0;i<90;i++){
                User user = new User();
                user.setDel(0);
                user.setName("Cr"+i);
                userService.add(user);
            }
        }
        @Test
        public void t9(){
            for (int i = 0;i<150;i++){
                User user = new User();
                user.setId(1);
                OrderTable orderTable = new OrderTable();
                orderTable.setUser(user);
                orderTable.setOrderCode("9359"+i);
                orderTable.setAddress("成都市"+i+"号公寓");
                orderTable.setMobile("13388388303"+i);
                orderTable.setReceivers("张三"+i);
                orderTable.setCreateTime(DateUtil.date());
                orderTable.setStatus(OrderTable.Status.UNPAID);
                orderTable.setOrderPrice(new BigDecimal(10));
                orderTable.setDeliveryTime(DateUtil.date());
                orderTable.setDel(0);
                List<OrderItem> items = orderTable.getOrderItemList();
                orderTable.setPostcode("66666");
                orderTableService.add(orderTable);

            }
        }
        @Test
        public void t10(){
            OrderTable table = new OrderTable();
            table.setId(1);
            OrderItem item = new OrderItem();
            item.setOrderTable(table);
            item.setCount(1);
            Product product1 = productService.query(6);
            item.setProduct(product1);
            final BigDecimal price;
            price =  product1.getNPrice();
            BigDecimal totalPrice = itemService.totalPrice(item.getCount(), price);
            item.setTotalPrice(totalPrice);
            item.setDel(0);
            item.setCreateTime(DateUtil.date());
            itemService.add(item);
        }
        @Test
        public void t11(){
            for (int i = 0 ;i<10;i++){
                Comment comment = new Comment();
                Product product = new Product();
                product.setId(6);
                comment.setProduct(product);
                comment.setDel(0);
                comment.setUser(userService.getUser(94));
                comment.setContent("测试评论！！！！"+i);
                comment.setCreateTime(DateUtil.date());
                commentService.add(comment);
            }
        }
        @Test
        public void t12(){
            List<Comment> list = commentService.list(7);
            System.out.println(list.size());
        }
        @Test
        public void t13(){
            List<Product> packages = searchService.likeQuery("手机","",-1);
            System.out.println(packages.size());
        }

}
