package com.kw.manager.error;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/* 自定义错误处理controller */
public class MyErrorController extends BasicErrorController {

    /*继承一个有参构造器*/
    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    /*
    {
    "timestamp": "2019-06-10 18:45:43",
    "status": 500,
    "error": "Internal Server Error",
    "exception": "java.lang.IllegalArgumentException",
    "message": "id不可为空",
    "path": "/manager/products"}
     */
    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        /* getErrorAttributes方法返回错误字段数据  Map集合-错误信息 */
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
        /* 业务只需要“message”属性 故而删除不需要的属性 */
        map.remove("timestamp");
        map.remove("status");
        map.remove("error");
        map.remove("exception");
        map.remove("path");
        String code = (String) map.get("message");  // map集合的值
        ErrorEnum errorenum = ErrorEnum.getByCode(code);
        map.put("message", errorenum.getMessage());
        map.put("code", errorenum.getCode());
        map.put("canRetry", errorenum.getCanRetry());
        return map;
    }
}
