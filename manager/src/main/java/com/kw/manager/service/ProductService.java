package com.kw.manager.service;

import com.kw.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品业务层接口
 */
public interface ProductService {

    Product addProduct(Product product);

    Product findOne(String id);

    Page<Product> query(List<String> idList, BigDecimal minRewardRate, BigDecimal maxRewardRate, List<String> statusList, PageRequest pageRequest);

}
