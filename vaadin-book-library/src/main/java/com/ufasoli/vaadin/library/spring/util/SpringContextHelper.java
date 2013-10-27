package com.ufasoli.vaadin.library.spring.util;

import com.ufasoli.vaadin.library.spring.config.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Ulises Fasoli
 * Date: 24.10.13
 * Time: 10:43
 */
public class SpringContextHelper {

    private ApplicationContext context;

    private static  SpringContextHelper instance;
    private SpringContextHelper() {

        context = new AnnotationConfigApplicationContext(Application.class);
    }

    public static SpringContextHelper helper(){

        if(instance == null){
            instance = new  SpringContextHelper();
        }
        return instance;
    }

    public Object getBean(final String beanRef) {
        return context.getBean(beanRef);
    }

    public <T> T getBean(Class<T> beanRef) {
        return context.getBean(beanRef);
    }
}
