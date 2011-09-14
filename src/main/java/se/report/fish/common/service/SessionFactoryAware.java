package se.report.fish.common.service;

import org.hibernate.SessionFactory;

/**
 * Fix to handle autowire of SessionFactory. In some cases where a
 * SessionFactory is injected with @AutoWire then the startup of the application
 * will fail. The reason for this is yet unknown therefore this is interface was
 * introduced.
 * 
 * 
 * @author Niklas Granryd, Cygni
 */
public interface SessionFactoryAware {
    public void setSessionFactory(SessionFactory sessionFactory);
}