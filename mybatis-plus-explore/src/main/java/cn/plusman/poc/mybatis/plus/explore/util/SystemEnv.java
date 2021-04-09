package cn.plusman.poc.mybatis.plus.explore.util;

import java.util.Properties;

/**
 * @author plusman
 * @since 2021/4/9 3:05 PM
 */
public class SystemEnv {
    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.putAll(System.getenv());
        return properties;
    }
}
