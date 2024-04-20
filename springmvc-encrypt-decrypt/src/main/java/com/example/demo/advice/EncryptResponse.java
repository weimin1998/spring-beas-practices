package com.example.demo.advice;

import com.example.demo.annotation.Encrypt;
import com.example.demo.config.EncryptProperties;
import com.example.demo.pojo.RespBean;
import com.example.demo.utils.AESUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@EnableConfigurationProperties(EncryptProperties.class)
@ControllerAdvice
// 这里的泛型，表示当接口的返回类型是该泛型时，才会加密
public class EncryptResponse implements ResponseBodyAdvice<RespBean> {
    private static final Logger logger = LoggerFactory.getLogger(EncryptResponse.class);
    private final ObjectMapper om = new ObjectMapper();

    @Autowired
    EncryptProperties encryptProperties;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 接口方法上标注了 Encrypt 注解才加密
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public RespBean beforeBodyWrite(RespBean body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            // 加密msg
            if (body.getMsg() != null) {
                body.setMsg(AESUtil.encrypt(body.getMsg().getBytes(), encryptProperties.getKey().getBytes()));
            }
            // 加密data
            if (body.getObj() != null) {
                body.setObj(AESUtil.encrypt(om.writeValueAsBytes(body.getObj()), encryptProperties.getKey().getBytes()));
            }
            // 加密后示例：
            // {
            //    "status": 200,
            //    "msg": "TU9vsZNE8/UP8dgrQCMFIQ==",
            //    "obj": "XI/Ze5j/B4A2P9V+df5KBZr6t3j7zySPXqs1rkJXXKY="
            // }
        } catch (Exception e) {
            logger.error("加密失败！" + e.getMessage());
        }
        return body;
    }
}