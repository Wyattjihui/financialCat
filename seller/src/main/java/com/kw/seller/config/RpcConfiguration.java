package com.kw.seller.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.kw.api.service.ProductRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * rpc服务相关配置
 */
@ComponentScan(basePackageClasses = {ProductRpcService.class})
@Configuration
@Slf4j
public class RpcConfiguration {
    /**
     * 导出一个bean，告诉spring创建一个rpc客户端，导出一个jsonprc客户端的一个代理，并告诉它去扫描哪些接口
     */
    @Bean
    public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.manager.url}")String url){
        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();

        try {
            creator.setBaseUrl(new URL(url));
        } catch (MalformedURLException e) {
            log.error("创建rpc服务地址错误，",e);
            e.printStackTrace();
        }
        /* rpc接口 */
        creator.setScanPackage(ProductRpcService.class.getPackage().getName());
        return creator;
    }
}
