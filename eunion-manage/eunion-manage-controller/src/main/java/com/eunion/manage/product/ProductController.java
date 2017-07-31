package com.eunion.manage.product;

import com.eunion.manage.entity.product.Product;
import com.eunion.manage.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ys on 2016/10/24.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "getAllData", method = RequestMethod.GET)
    @ResponseBody
    public Object getAllProductData() {
        return productService.getAllProductService();
    }

    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    @ResponseBody
    public Object addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @RequestMapping(value = "deleteProduct", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteProduct(Product product) {
        return productService.deleteProduct(product);
    }

    @RequestMapping(value = "updateProduct", method = RequestMethod.PUT)
    @ResponseBody
    public Object updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @RequestMapping(value = "addProductProperties", method = RequestMethod.POST)
    @ResponseBody
    public Object addProductProperties(@RequestBody Product product) {
        return productService.addProductProperties(product);
    }

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        if (!file.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            System.out.println("----------------文件路径:" + path);
            String fileName = file.getOriginalFilename();
            File targetFile = new File(path, fileName);
            //目录不存在，则创建目录
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //保存
            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            map.put("status", "000000");
            map.put("msg", "[" + fileName + "]文件上传成功");
            return map;
        }
        map.put("status", "000001");
        map.put("msg", "文件上传失败");
        return map;
    }
}
