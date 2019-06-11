package com.kw.manager.error;

/* 枚举错误种类 */
public enum ErrorEnum {

    ID_NOT_NULL("F001","id不可为空",false),
    NAME_NOT_NULL("F002","名称不可为空",false),
    THRESHOLDAMOUNT_NOT_NULL("F003","起投金额不可为空",false),
    STEPAMOUNT_NOT_NULL("F004","投资步长不可为空",false),
    LOCKTERM_NOT_NULL("F005","锁定期不可为空",false),
    REWARDRATE_NOT_NULL("F006","收益率不可为空",false),
    STATUS_NOT_NULL("F007","状态不可为空",false),
    UNKNOWN("999","未知异常",false)
    ;

    private String code;//错误编码
    private String message;//错误消息
    private Boolean canRetry;//错误重试

    ErrorEnum(String code, String message, Boolean canRetry) {
        this.code = code;
        this.message = message;
        this.canRetry = canRetry;
    }

    public static ErrorEnum getByCode(String code) {
        for (ErrorEnum value : ErrorEnum.values()) {
            if (value.getCode().equals(code)){
                return value;
            }
        }
        return UNKNOWN;  /* 枚举：未知异常 */
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public Boolean getCanRetry() {
        return canRetry;
    }
}
