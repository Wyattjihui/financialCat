package com.kw.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Product implements Serializable {

    @Id
    private String id; //编号
    private String name; //名称
    private BigDecimal thresholdAmount; //起投金额
    private BigDecimal stepAmount; //投资步长
    private Integer lockTerm; //锁定期 单位天
    private BigDecimal rewardRate; //收益率 0-100，百分比
    /**
     * @see com.kw.enums.StatusEnum
     */
    private String status; //状态 审核中：AUDINTING，销售中：INSELL，暂停销售：LOCKED，已结束：FINISHED
    private String memo ; //备注

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createAt; //创建时间
    private String createUser; //创建者ID

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updateAt; //更新时间
    private String updateUser; //更新者ID

    @Override
    public String toString() {
        // 动态生成 ToString
        return ReflectionToStringBuilder.toString(this);
    }
}
