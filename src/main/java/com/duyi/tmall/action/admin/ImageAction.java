package com.duyi.tmall.action.admin;

import cn.hutool.core.io.FileUtil;

import cn.hutool.core.util.IdUtil;
import com.duyi.tmall.bean.Category;
import com.duyi.tmall.bean.Image;
import com.duyi.tmall.bean.Product;
import com.duyi.tmall.service.base.BaseCategoryService;
import com.duyi.tmall.service.base.BaseImageService;
import com.duyi.tmall.service.base.BaseProductService;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 图片action
 */
@ParentPackage("struts-default")
@Namespace("/admin/product/image")
@Results({
        @Result(name = "list",location = "/admin/listProductImage.jsp"),
        @Result(name = "add",location = "list?product.id=${product.id}&category.id=${category.id}",type = "redirect"),
        @Result(name = "delete",location = "list?product.id=${product.id}&category.id=${category.id}",type = "redirect")
})
@Getter
@Setter
public class ImageAction extends ActionSupport {
    /**
     * 上传的文件
     */
    private File imgFile;
    /**
     * 文件类型
     */
    private String imgFileContentType;
    /**
     * 项目路径下的文件夹地址
     */
    private String imgFileFileName;
    /**
     * 文件夹名
     */
    private String serverSavePath = "/images/";
    /**
     * 图片的类型
     */
    private int type;
    private Image image;
    private Product product;
    private Category category;
    @Autowired
    private BaseImageService baseImageService;
    @Autowired
    private BaseProductService productService;
    @Autowired
    private BaseCategoryService baseCategoryService;
    @Action("list")
    public String list(){
        //获取商品对象中的图片集合 并分类好发送给前端
        product = productService.query(product.getId());
        category = baseCategoryService.query(category.getId());
        return "list";
    }
    @Action("add")
    public String add(){
        //创建文件不重复的名字
        String name = IdUtil.simpleUUID();
        //将上传的文件名字截取类型 .jsp .pgg等
        String format = imgFileFileName.substring(imgFileFileName.lastIndexOf("."));
        //拼接成一个自定义的文件名称
        String nickname = name +format;
        //获取servlet原生对象
        HttpServletRequest request = ServletActionContext.getRequest();
        //获取当前项目相对路径 /tamll
        String contextPath = request.getContextPath();
        //获取图片地址 D:\JAVA\javaSoftware\apache-tomcat-9.0.34\webapps\tmall\images\
        String diskSavePath = request.getServletContext().getRealPath(serverSavePath);
        String a = System.getProperty("image");
        //访问地址  拼接 可以在浏览器上访问的地址http://localhost
        String urlImage = "http://localhost:8080" + contextPath + serverSavePath +nickname;
        File file = new File(diskSavePath,nickname);
        //将文件赋值到磁盘文件中
        //imgFile上传的文件 file新建的文件 true可覆盖
        FileUtil.copy(imgFile,file,true);
        product.setId(product.getId());
        image.setProduct(product);
        image.setUrlImage(urlImage);
        image.setDel(0);
        switch (type){
            case 1:
                //如果传过来的类型1 则表示是商品的封面图片
                image.setType(Image.Type.COVER);
                break;
            case 2:
                //如果传过来的类型2 则表示是商品的头部图片
                image.setType(Image.Type.TOP);
                break;
            default:
                //如果传过来的类型3 则表示是商品的详情图片
                image.setType(Image.Type.DETAIL);
        }
        baseImageService.add(image);
        return "add";
    }
    @Action("delete")
    public String delete(){
        Image image = baseImageService.get(this.image.getId());
        baseImageService.delete(image);
        return "delete";
    }
}

