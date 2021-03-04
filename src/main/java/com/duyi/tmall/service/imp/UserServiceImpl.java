package com.duyi.tmall.service.imp;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.duyi.tmall.bean.Massage;
import com.duyi.tmall.bean.Pagination;

import com.duyi.tmall.bean.User;
import com.duyi.tmall.dao.base.BaseUserDao;
import com.duyi.tmall.service.base.BaseUserService;

import com.duyi.tmall.util.SendEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author 用户service
 */
@Service
public class UserServiceImpl implements BaseUserService {
    @Autowired
    private BaseUserDao userDao;
    private int num;
    /**生成秘钥*/
    private static final String SECRET_KEY = "sdkweopkrf[weor-=";
    public Pagination getList(int pageNum) {
        int count = userDao.count();
        Pagination<User> pagination = Pagination.getPagination(count,pageNum);
        List<User> userList = (List<User>) userDao.getList(pagination.getFirst(),pagination.getMax());
        pagination.setData(userList);
        pagination.setNum(count);
        return pagination;
    }

    public void add(User user) {
        userDao.insert(user);
    }

    public boolean get(User user) {
        //判断对象是否为空 或则 名字密码是否为null
        if (ObjectUtil.isEmpty(user) ||
            ObjectUtil.isEmpty(user.getName())||
            ObjectUtil.isEmpty(user.getPassword())){
            return false;
        }
        //判断用户名是否存在

        if( userDao.get(user.getName()) != null){
            return false;
        }
        if (num !=user.getVerify()){
            //验证码不一致
            return false;
        }
        user.setCreateTime(DateUtil.date());
        user.setDel(0);
        user.setGro(User.Gro.ORDINARY);
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));

        userDao.insert(user);
        return true;
    }

    public boolean login(User user) {
        //判断对象是否为空 或则 名字密码是否为null
        if (ObjectUtil.isEmpty(user) ||
                ObjectUtil.isEmpty(user.getName())||
                ObjectUtil.isEmpty(user.getPassword())){
            return false;
        }
        User u = userDao.get(user.getName());
        if (u == null){
            return false;
        }
        return u.getPassword().equals(DigestUtil.md5Hex(user.getPassword()));
    }

    public Massage sendEmail(User user) {
        Massage massage;
        User user1 = userDao.sendEmail(user.getEmail());
        if (user1 != null){
            massage = new Massage();
            massage.setMsg("该邮箱已被注册");
            massage.setData(400);
            return massage;
        }
        num = getNum();
        if (!SendEmailUtil.setEmailSend("淘淘宝",user.getEmail(),"您正在注册淘淘宝vip账号,以下是您的验证码"+ num +"请注意查收")){
            massage = new Massage();
            massage.setMsg("发送失败,邮箱不合法");
            massage.setData(400);
            return massage;
        }
        massage = new Massage();
        massage.setMsg("验证码已发送至该邮箱");
        massage.setCode(200);
        return massage;
    }

    public String findPassEmail(User user) {
        User dbUser = userDao.sendEmail(user.getEmail());
        if (dbUser == null){
            return null;
        }
        //对象名称
        String name = dbUser.getName();
        //当前时间
        String time = "" + System.currentTimeMillis();
        String mdStr = DigestUtil.md5Hex(name + time + SECRET_KEY);
        String params = mdStr + "&" + name + "&" +time;
        String token = Base64.encode(params);
        String url = "http://localhost:8080/tmall/newPass?token="+token;
        SendEmailUtil.setEmailSend("淘淘宝",dbUser.getEmail(),"您正在更改淘淘宝账号密码,请勿告诉他人,修改密码地址:"+url);
        return name;
    }

    public String verifyToken(String token) {
        if (StrUtil.isEmpty(token)){
            return null;
        }
        try {
            //将token解密
            String params = Base64.decodeStr(token);
            //拆分对应的数据
            String[] array = params.split("&");
            //将拆分出来的数据都加密 好判断和数据是否有过更改
            String encryption = DigestUtil.md5Hex(array[1] + array[2] + SECRET_KEY);
            //判断array[0] = mdStr于 拼接的encryption 是否相同 若不同则被更改
            if (!array[0].equalsIgnoreCase(encryption)){
                return null;
            }
            //获取当前时间
            long now = System.currentTimeMillis();
            //获取发送邮件时的时间 并转换成字符串
            long time = Long.parseLong(array[2]);
            //30分钟过期 最迟时间
            long expire = 60 * 30 * 1000;
            //当前时间 - 发送时的时间 若大于30分钟 则失效
            if (now - time >= expire){
                return null;
            }
            //返回名字
            return array[1];
        }catch (Exception e){
            return null;
        }

    }

    public void modifyPass(User user) {
        User u = userDao.get(user.getName());
        u.setPassword(DigestUtil.md5Hex(user.getPassword()));
        userDao.update(u);
    }

    public User getUser(int id) {

        return (User) userDao.query(id);
    }

    public User gerUser(String name) {
        return userDao.get(name);
    }

    /**
     * 返回一个定长的随机纯数字 字符串
     * @return 返回字符
     */
    private int getNum(){
        Random random = new Random();
        return random.nextInt(100000);
    }

}
