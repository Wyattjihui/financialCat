package com.kw.manager.service.impl;

import com.kw.entity.Product;
import com.kw.enums.StatusEnum;
import com.kw.manager.error.ErrorEnum;
import com.kw.manager.repositories.ProductRepository;
import com.kw.manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 创建产品服务类用于实现产品业务层接口
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    /**
     * 添加产品
     * @param product 产品信息
     * @return
     */
    @Override
    public Product addProduct(Product product) {
        log.debug("创建产品，参数：{}",product);
        // 自定义产品数据校验的方法
        checkProduct(product);
        // 自定义设置产品默认值的方法
        setDefault(product);
        Product result = productRepository.save(product);
        log.debug("创建产品，结果：{}",result);
        return result;
    }

    /**
     * 查询单个产品
     * @param id 产品编号
     * @return
     */
    @Override
    public Product findOne(String id) {
        log.debug("查询单个产品，id={}",id);
        Product product = productRepository.findOne(id);
        log.debug("查询单个产品，结果={}",product);
        return product;
    }

    /**
     * 分页查询产品
     * @param idList 产品编号
     * @param minRewardRate 最小收益率
     * @param maxRewardRate 最大收益率
     * @param statusList 状态
     * @param pageRequest 分页信息
     * @return
     */
    @Override
    public Page<Product> query(List<String> idList, BigDecimal minRewardRate, BigDecimal maxRewardRate, List<String> statusList, PageRequest pageRequest) {
        log.info("查询产品，idList={},minRewardRate={},maxRewardRate={},statusList={},pageRequest={}"
                ,idList,minRewardRate,maxRewardRate,statusList,pageRequest);
        Specification<Product> specification = new Specification<Product>(){
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Expression<String> idCol = root.get("id");
                Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
                Expression<String> statusCol = root.get("status");
                List<Predicate> predicates = new ArrayList<>();
                if (idList!=null && idList.size()>0){
                    predicates.add(idCol.in(idList));
                }
                if (minRewardRate!=null && BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
                    predicates.add(cb.ge(rewardRateCol, minRewardRate));
                }
                if (maxRewardRate!=null && BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
                    predicates.add(cb.le(rewardRateCol, maxRewardRate));
                }
                if (statusList!=null && statusList.size()>0){
                    predicates.add(statusCol.in(statusList));
                }
                query.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };
        Page<Product> page = productRepository.findAll(specification, pageRequest);
        log.debug("查询产品，结果={}", page);
        return page;
    }

    /**
     * 设置默认值
     * 创建时间，更新时间，投资步长，锁定期
     * @param product
     */
    private void setDefault(Product product) {
        if (product.getCreateAt() == null){
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt() == null){
            product.setUpdateAt(new Date());
        }
        if (product.getStepAmount() == null){
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm() == null){
            product.setLockTerm(0);
        }
        if (product.getStatus() == null){
            product.setStatus(StatusEnum.AUDINTING.name());
        }
    }

    /**
     * 产品数据校验
     * 1.非空校验
     * 2.收益率要0-30以内
     * 3.投资步长需为整数
     * @param product
     */
    private void checkProduct(Product product) {
        Assert.notNull(product.getId(), ErrorEnum.ID_NOT_NULL.getCode());
        Assert.notNull(product.getName(),ErrorEnum.NAME_NOT_NULL.getCode());
        Assert.notNull(product.getThresholdAmount(),ErrorEnum.THRESHOLDAMOUNT_NOT_NULL.getCode());
        Assert.notNull(product.getStepAmount(),ErrorEnum.STEPAMOUNT_NOT_NULL.getCode());
        Assert.notNull(product.getLockTerm(),ErrorEnum.LOCKTERM_NOT_NULL.getCode());
        Assert.notNull(product.getRewardRate(),ErrorEnum.REWARDRATE_NOT_NULL.getCode());
        Assert.notNull(product.getStatus(),ErrorEnum.STATUS_NOT_NULL.getCode());

        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate())<0
            && BigDecimal.valueOf(30).compareTo(product.getRewardRate())>=0,"收益率范围(0-30)错误");
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue())
                .compareTo(product.getStepAmount())==0,"投资步长需为整数");
    }
}
