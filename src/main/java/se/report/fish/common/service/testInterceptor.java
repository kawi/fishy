package se.report.fish.common.service;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

@Component
public class testInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = 1307374274488937645L;

    @Override
    public boolean onFlushDirty(final Object entity, final Serializable id, final Object[] currentState,
	    final Object[] previousState, final String[] propertyNames, final Type[] types) {

	return false;
    }

}