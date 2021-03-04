package com.duyi.tmall.service.base;


import com.duyi.tmall.bean.Massage;
import com.duyi.tmall.bean.Pagination;
import com.duyi.tmall.bean.User;

/**
 * @author 用户serive
 */
public interface BaseUserService {
    /**
     * 根据对象返回对应的页码数据
     *
     * @param pageNum 从第几个页码开始展示
     * @return 返回对象所对应的页码数据
     */
     Pagination getList(int pageNum);

    /**
     * 插入一条信息
     * @param user user对象
     */
     void add(User user);

    /**
     * 根据对象名字查找对应的用户信息
     * @param user 用户对象
     * @return 是否让注册
     */
     boolean get(User user);

    /**
     * 根据输入的对象判断是否正确
     * @param user 前端输入的用户对象
     * @return 是否正确
     */
     boolean login(User user);

    /**
     * 接受ajax的邮箱信息 并判断该该邮箱是否被注册 或格式不正确
     * @param user 新增的用户对象
     * @return 返回响应数据
     */
     Massage sendEmail(User user);

    /**
     * 通过对象中的邮箱查询一个对象
     * @param user 对象
     * @return 返回一个url更改密码的路径
     */
     String findPassEmail(User user);

    /**
     * 根据返回回来的token进行判断前端传输过来的token是否是正确的
     * @param token 秘钥 + 用户名 + 创建时间
     * @return 返回用户名
     */
     String verifyToken(String token);

    /**
     * 更新用户的数据
     * @param user 要更新的用户对象
     */
     void modifyPass(User user);

    /**
     * 根据id查询一个用户
     * @param id 用户id
     * @return 返回一个对象
     */
     User getUser(int id);

    /**
     * 根据用户名查询一个对象
     * @param name 用户名
     * @return 用户对象
     */
     User gerUser(String name);
}
