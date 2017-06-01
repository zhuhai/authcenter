package com.zhuhai.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/6/1
 * Time: 15:40
 */
public class RequestParameterUtil {

    /**
     * 移除url中的auth_code和auth_username参数
     * @param request
     * @return
     */
    public static String getParameterWithOutCode(HttpServletRequest request) {
        StringBuffer backUrl = request.getRequestURL();
        String params = "";
        Map<String, String[]> paramMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            if (!"auth_code".equals(entry.getKey()) && !"auth_username".equals(entry.getKey())) {
                if (params.equals("")) {
                    params = entry.getKey() + "=" + entry.getValue();
                } else {
                    params = "&" + entry.getKey() + "=" + entry.getValue();
                }

            }
        }
        if (StringUtils.isNotBlank(params)) {
            backUrl = backUrl.append("?").append(params);
        }
        return backUrl.toString();
    }
}
