package com.model.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

/**
 * @classname : JsonUtil
 * @description: JSON 工具类
 * @author: tianqikai
 * @date : 2021/2/26 23:24
 */
public final class JackJsonUtil {

//    private static final Logger LOGGER = Logger.getLogger(JsonUtil.class);
//
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    /**
//     * 将POJO转为JSON
//     */
//    public static <T> String toJson(T obj) {
//        String json;
//        try {
//            json = OBJECT_MAPPER.writeValueAsString(obj);
//        } catch (Exception e) {
//            LOGGER.error("convert POJO to JSON failure", e);
//            throw new RuntimeException(e);
//        }
//        return json;
//    }
//
//    /**
//     * 将JSON转为POJO
//     */
//    public static <T> T fromJson(String json, Class<T> type) {
//        T pojo;
//        try {
//            pojo = OBJECT_MAPPER.readValue(json, type);
//        } catch (Exception e) {
//            LOGGER.error("convert JSON to POJO failure", e);
//            throw new RuntimeException(e);
//        }
//        return pojo;
//    }
}