package com.kw.api.service;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.kw.api.pojo.ProductRpcReq;
import com.kw.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 产品相关rpc服务
 */
@JsonRpcService("rpc/product")
public interface ProductRpcService {

    /**
     * 查询所有产品
     * @param req
     * @return
     */
    List<Product> query(ProductRpcReq req);

    /**
     * 查询单个产品
     * @param id
     * @return
     */
    Product findOne(String id);


}
