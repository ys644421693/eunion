package com.eunion.manage.service.product;

import com.eunion.manage.entity.product.Product;

import java.util.List;

/**
 * Created by ys on 2016/10/24.
 */
public interface ProductService {

     List<Product> getAllProductService();

    Object addProduct(Product product);

    Object deleteProduct(Product product);

    Object updateProduct(Product product);

    Object addProductProperties(Product product);
}
