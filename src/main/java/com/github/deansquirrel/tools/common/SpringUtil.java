package com.github.deansquirrel.tools.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(this.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }

    public Object getObjectBean(Class<?> clazz) {
        return this.applicationContext.getBean(clazz);
    }

    public <T> T getBean(Class<T> clazz) {
        return this.applicationContext.getBean(clazz);
    }
}
