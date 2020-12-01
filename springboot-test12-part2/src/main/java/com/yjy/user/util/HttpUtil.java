package com.yjy.user.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 请求方法疯转
     * @param url 请求地址
     * @param method 请求方法，post，get，delete等
     * @param entity 请求参数
     * @param result 请求结果
     * @param <P>
     * @param <T>
     * @return
     */
    public static <P, T> T httpRequest(String url, HttpMethod method, HttpEntity<P> entity, Class<T> result){
        // TODO RestTemplate需改造成@Autowired引入
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<T> responseEntity = (ResponseEntity<T>) restTemplate.exchange(url, method, entity, result);
            return responseEntity.getBody();
        } catch (Exception e) {
            logger.error("请求失败： " + e.getMessage());
        }
        return null;
    }
}
