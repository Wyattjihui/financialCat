package com.kw.manager.repositories;

import com.kw.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 创建产品repository接口
 */
public interface ProductRepository extends JpaRepository<Product,String>,JpaSpecificationExecutor<Product> {
}
