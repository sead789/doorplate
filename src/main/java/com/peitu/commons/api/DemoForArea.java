package com.peitu.commons.api;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rising
 * @date 2019/6/12
 */
public class DemoForArea {

    private static final String HOST = "https://api02.aliyun.venuscn.com";
    /**
     * 根据区域等级获取区域接口_全国省市区县行政区域查询
     */
    private static final String PATH_1 = "/area/all";
    /**
     * 根据上级ID获取区域_全国省市区县行政区域查询
     */
    private static final String PATH_2 = "/area/query";

    private static final String METHOD = "GET";

    private static final String APPCODE = "23e42441ba83418db50aee12365c5025";


    /**
     * 根据上级ID获取区域_全国省市区县行政区域查询
     *
     * @param parentId
     * @return
     */
    public static String getAdByParentId(int parentId) {
        String host = HOST;
        String path = PATH_2;
        String method = METHOD;
        String appcode = APPCODE;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("parent_id", parentId + "");

        return getContent(host, path, method, headers, querys);
    }

    /**
     * 根据区域等级获取区域接口_全国省市区县行政区域查询
     *
     * @param level
     * @param page
     * @param size
     * @return
     */
    public static String getAllArea(String level, String page, String size) {
        String host = HOST;
        String path = PATH_1;
        String method = METHOD;
        String appcode = APPCODE;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("level", level);
        querys.put("page", page);
        querys.put("size", size);

        return getContent(host, path, method, headers, querys);
    }

    private static String getContent(String host, String path, String method, Map<String, String> headers, Map<String, String> querys) {
        String content = "";
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());
            //获取response的body
            content = EntityUtils.toString(response.getEntity());
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

}
