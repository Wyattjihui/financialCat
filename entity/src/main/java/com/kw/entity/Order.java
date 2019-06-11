package com.kw.entity;

import com.kw.enums.OrderStatusEnum;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "order_t")
public class Order implements Serializable {

    @Id
    private String orderId;//订单编号
    private String chanId;//渠道编号 套壳公司编号
    private String productId;//产品编号
    private String chanuserId;//用户编号 套壳公司的用户编号

    /**
     * @see com.kw.enums.OrderTypeEnum
     */
    private String ordertyPe;//订单类型  申购：APPLY，赎回：REDEEM

    /**
     * @see OrderStatusEnum
     */
    private String orderstatUs;//订单状态 初始化：INIT， 处理中：PROCESS，成功：SUCCESS，失败：FAIL

    private String outerorderId;//外部订单编号 套壳公司的订单编号
    private BigDecimal amount;//订单金额
    private String memo;//备注
    private Date createAt;//创建时间
    private Date updateAt;//更新时间

    @Override
    public String toString() {
        // 动态生成 ToString
        return ReflectionToStringBuilder.toString(this);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getChanuserId() {
        return chanuserId;
    }

    public void setChanuserId(String chanuserId) {
        this.chanuserId = chanuserId;
    }

    public String getOrdertyPe() {
        return ordertyPe;
    }

    public void setOrdertyPe(String ordertyPe) {
        this.ordertyPe = ordertyPe;
    }

    public String getOrderstatUs() {
        return orderstatUs;
    }

    public void setOrderstatUs(String orderstatUs) {
        this.orderstatUs = orderstatUs;
    }

    public String getOuterorderId() {
        return outerorderId;
    }

    public void setOuterorderId(String outerorderId) {
        this.outerorderId = outerorderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
