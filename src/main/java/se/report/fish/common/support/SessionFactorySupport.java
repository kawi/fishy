package se.report.fish.common.support;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Support class to get session factory. All services that needs a
 * SessionFactory must inherit this class. This is due to problems when a
 * Controller (loaded from riot setup.xml) requires services that depends on a
 * SessionFactory. The real problem behind this is not yet known.
 * 
 * This will lazy fetch the session factory when needed from the application
 * context.
 */
public abstract class SessionFactorySupport implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private SessionFactory sessionFactory;

    /**
     * Will lazy load the session factory from the application context if not
     * already set.
     * 
     * @return
     */
    protected SessionFactory getSessionFactory() {
	if (this.sessionFactory == null) {
	    this.sessionFactory = this.applicationContext.getBean(SessionFactory.class);
	}
	return this.sessionFactory;
    }

    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
	this.applicationContext = applicationContext;
    }

    /**
     * Typically used only for testing purposes. Normally, the session factory
     * is retrieved from the application context due to lazy init problems.
     * 
     * @param sessionFactory
     *            The session factory.
     */
    public void setSessionFactory(final SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

}
