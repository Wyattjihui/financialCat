package com.kw.manager.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.kw.api.pojo.ProductRpcReq;
import com.kw.api.service.ProductRpcService;
import com.kw.entity.Product;
import com.kw.manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Rpc服务实现类
 */
@AutoJsonRpcServiceImpl
@Service
@Slf4j
public class ProductRpcServiceManagerImpl implements ProductRpcService {

    @Autowired
    ProductService productService;

    @Override
    public List<Product> query(ProductRpcReq req) {
        log.info("查询多个产品，请求参数：{}",req);
        PageRequest pageable = new PageRequest(0, 1000, Sort.Direction.DESC, "rewardRate");
        Page<Product> result = productService.query(req.getIdList(), req.getMinRewardRate()
                , req.getMaxRewardRate(), req.getStatusList(),pageable);
        log.info("查询多个产品，请求结果：{}",result);
        return result.getContent();
    }

    @Override
    public Product findOne(String id) {
        log.info("查询单个产品，请求参数：{}",id);
        Product product = productService.findOne(id);
        log.info("查询多个产品，请求结果：{}",product);
        return product;
    }
}
