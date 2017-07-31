package com.eunion.manage.serviceimpl.product;

import com.eunion.manage.common.util.PulicClass;
import com.eunion.manage.dto.BaseResponse;
import com.eunion.manage.entity.product.Product;
import com.eunion.manage.repository.ProductRepository;
import com.eunion.manage.service.product.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by ys on 2016/10/24.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProductService() {
        return productRepository.findAll();
    }

    @Override
    public Object addProduct(Product product) {
        return updateProduct(product);
    }

    @Override
    public Object deleteProduct(Product product) {
        productRepository.delete(product);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus("000000");
        baseResponse.setMsg("删除成功！");
        return baseResponse;
    }

    @Override
    public Object updateProduct(Product product) {
        BaseResponse baseResponse = new BaseResponse();
        Product p = null;
        try {
            p = productRepository.save(product);
        } catch (ConstraintViolationException e) {
            baseResponse.setMsg(PulicClass.getErrorMessage(e.getMessage()));
            baseResponse.setStatus("000001");
            return baseResponse;
        }
        return p;
    }

    @Override
    public Object addProductProperties(Product product) {
        return updateProduct(product);
    }
}
