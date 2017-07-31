package com.eunion.manage.repository;

import com.eunion.manage.entity.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ys on 2016/10/24.
 */
@Repository
public interface ProductRepository  extends CrudRepository<Product,Long>{

    public List<Product> findAll();
}
