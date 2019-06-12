package com.kw.api.pojo;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductRpcReq {

    private List<String> idList;
    private BigDecimal minRewardRate;
    private BigDecimal maxRewardRate;
    private List<String>  statusList;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
