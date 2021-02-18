package com.model.conf;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * ConfigurationManager :
 * @description: 读配置文件工具类
 * @author: tianqikai
 * @date : 2021/1/25 0025
 */
public class ConfigurationManager {
    private static Logger log=LogManager.getLogger(ConfigurationManager.class);
    private static Properties configProps= new Properties();
    private static String ConfigFile="tx.properties";
    static {
        try {
            configProps.load(ConfigurationManager.class.getResourceAsStream(ConfigFile));
        } catch (IOException e) {
            log.error("load config file Error:",e);
        }
    }
    /**
     * @methodname: getConfigValue
     * @description: 获取key对应的value值
     * @param configKey
     * @return: value
     * @author: tianqikai
     * @time: 2021/1/25 0025 15:47
     */
    public static String getConfigValue(String configKey){
        return configProps.getProperty(configKey).trim();
    }
    /**
     * @methodname: getConfigValueAsInt
     * @description: 获取key对应的value值
     * @param configKey
     * @return: int
     * @author: tianqikai
     * @time: 2021/1/25 0025 15:47
     */
    public static int getConfigValueAsInt(String configKey){
        return Integer.parseInt(configProps.getProperty(configKey).trim());
    }

}
