package org.example.app.services;

import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class IdProvider implements InitializingBean, DisposableBean, BeanPostProcessor {

    public String providerId(Book book){
        return this.hashCode() + "_" + book.hashCode();
    }

    private void initIdProvider() {
        System.out.println("provider INIT");
    }

    private void destroyIdProvider() {
        System.out.println("provider DESTROY");
    }

    private void defaultDestroy(){
        System.out.println("default DESTROY in provider");
    }

    private void defaultInit(){
        System.out.println("default INIT in provider");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("provider afterPropertiesSet invoked");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposibleBean destroy invoked ");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization invoked by bean " + beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization invoked by bean " + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @PostConstruct
    public void postConstructProvider(){
        System.out.println(" postConstructProvider annotated method called ");
    }

    @PreDestroy
    public void preDestroyIdProvider(){
        System.out.println(" preDestroyIdProvider annotated method called ");
    }
}
