package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class IdProvider implements InitializingBean, DisposableBean, BeanPostProcessor {


    public String provideId(Book book) {
        return this.hashCode() + "_" + book.hashCode();
    }

    private void initIdProvider() {
    }

    private void destroyIdProvider() {
    }

    private void defaultInit(){

    }

    private void defaultDestroy(){

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }


    public void postConstructIdProvider(){

    }


    public void preDestroyIdProvider(){

    }
}
