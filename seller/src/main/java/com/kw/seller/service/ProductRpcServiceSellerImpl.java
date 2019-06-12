package com.kw.seller.service;

import com.kw.api.pojo.ProductRpcReq;
import com.kw.api.service.ProductRpcService;
import com.kw.entity.Product;
import com.kw.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户端产品相关服务
 */
@Service
@Slf4j
public class ProductRpcServiceSellerImpl {

    @Autowired
    ProductRpcService productRpcService;

    public List<Product> findAll(){
        ProductRpcReq req = new ProductRpcReq();
        List<String> status = new ArrayList<>();
        status.add(StatusEnum.IN_SELL.name());
        req.setStatusList(status);
        log.info("rpc查询全部产品，参数：{}",req);
        List<Product> result = productRpcService.query(req);
        log.info("rpc查询全部产品，结果：{}",result);
        return result;
    }

    public Product findOne(String id){
        log.info("rpc查询单个产品，请求：{}", id);
        Product result = productRpcService.findOne(id);
        log.info("rpc查询单个产品，结果：{}", result);
        return result;
    }

    /* 测试方法 */
    @PostConstruct
    public void testFindAll(){
        findAll();
    }

    /* 测试方法 */
//    @PostConstruct
//    public void init(){
//        findOne("1");
//    }
}
