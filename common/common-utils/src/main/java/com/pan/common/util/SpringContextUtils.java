package com.pan.common.util;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextUtils implements ApplicationContextAware {
    /**
     * 上下文对象
     */
    private static final AppContainer APP_CONTAINER = new AppContainer();

    /**
     * 获取ApplicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return APP_CONTAINER.getApplicationContext();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        APP_CONTAINER.setApplicationContext(applicationContext);
    }

    /**
     * 通过clazz,从spring容器中获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 获取某一类型的bean集合
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 通过name和clazz,从spring容器中获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取配置文件配置项的值
     * @param key 配置项key
     */
    public static String getEnvironmentProperty(String key) {
        return getApplicationContext().getEnvironment().getProperty(key);
    }

    /**
     * 获取spring.profiles.active
     */
    public static String[] getActiveProfile() {
        return getApplicationContext().getEnvironment().getActiveProfiles();
    }

    /**
     * 静态内部类，用于存放ApplicationContext
     */
    @Data
    public static class AppContainer {
        private ApplicationContext applicationContext;
    }

}