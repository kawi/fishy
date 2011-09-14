package se.report.fish.common.service.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import se.report.fish.common.service.SessionFactoryAware;

public class SessionFactoryAwareProcessor implements BeanPostProcessor {

    private final SessionFactory sessionFactory;

    public SessionFactoryAwareProcessor(final SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    // @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName)
	    throws BeansException {
	if (bean instanceof SessionFactoryAware) {
	    ((SessionFactoryAware) bean).setSessionFactory(this.sessionFactory);
	}
	return bean;
    }

    // @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName)
	    throws BeansException {
	return bean;
    }

}
