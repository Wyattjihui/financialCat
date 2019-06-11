package com.kw.manager.controller;

import com.kw.entity.Product;
import com.kw.manager.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 添加产品
     * @param product
     * @return
     */
    @PostMapping
    /* swagger文档显示详细注释说明 */
    @ApiOperation(value = "创建产品",notes = "根据对应业务规则添加响应的产品")
    public Product addProduct(@RequestBody Product product){
        log.info("创建产品，参数：{}",product);
        Product result = productService.addProduct(product);
        log.info("创建产品，结果：{}",result);
        return result;
    }

    /**
     * 查询单个产品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    /* swagger文档显示详细注释说明 */
    @ApiOperation(value = "查询单个产品",notes = "根据产品id查询产品信息")
    public Product findOne(@PathVariable String id){
        log.info("查询单个产品，id={}",id);
        Product result = productService.findOne(id);
        log.info("查询单个产品，结果={}",result);
        return result;
    }

    /**
     * 分页查询
     * @param ids  产品编号
     * @param minRewardRate  最小收益率
     * @param maxRewardRate  最大收益率
     * @param status  状态
     * @param pageNum  当前页
     * @param pageSize 分页单位
     * @return
     */
    @GetMapping
    /* swagger文档显示详细注释说明 */
    @ApiOperation(value = "分页查询",notes = "根据对应业务规则分页查询产品")
    public Page<Product> query(String ids, BigDecimal minRewardRate,BigDecimal maxRewardRate,
                               String status,@RequestParam(defaultValue = "0")int pageNum,
                               @RequestParam(defaultValue = "10")int pageSize){
        log.info("分页查询产品，id={},minRewardRate={},maxRewardRate={}" +
                        ",status={},pageNum={},pageSize={}"
                ,ids,minRewardRate,maxRewardRate,status,pageNum,pageSize);
        // 定义空集合存放 产品id 和 状态status
        List<String> idList = null,statusList = null;
        if (!StringUtils.isEmpty(ids)){
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)){
            statusList = Arrays.asList(status.split(","));
        }
        // 分页
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Product> page = productService.query(idList, minRewardRate, maxRewardRate, statusList, pageRequest);
        log.info("分页查询产品，结果={}",page);
        return page;
    }

}
