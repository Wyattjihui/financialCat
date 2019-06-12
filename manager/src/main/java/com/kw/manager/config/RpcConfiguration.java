package com.kw.manager.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rpc相关配置
 */
@Configuration
public class RpcConfiguration {

    @Bean   /* 把RPC服务交给spring来管理 */
    public AutoJsonRpcServiceImplExporter rpcServiceImplExporter(){
        return new AutoJsonRpcServiceImplExporter();
    }

}
