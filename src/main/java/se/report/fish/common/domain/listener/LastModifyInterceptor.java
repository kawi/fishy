package se.report.fish.common.domain.listener;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.riotfamily.components.model.Content;
import org.riotfamily.components.model.ContentContainer;
import org.riotfamily.components.model.ContentContainerOwner;
import org.springframework.stereotype.Component;

import se.report.fish.common.domain.model.BaseContentEntity;

@Component
public class LastModifyInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = -8904671469141407925L;

    /**
     * Sets the modifiedAt attribute (current date) on a BaseContentEntity when
     * saved.
     */
    @Override
    public boolean onSave(final Object entity, final Serializable id, final Object[] state,
	    final String[] propertyNames, final Type[] types) {

	if (entity instanceof Content) {
	    final ContentContainer contentContainer = ((Content) entity).getContainer();
	    // FIXME: NULL CHECK HERE? WHY?
	    if (contentContainer != null) {
		final ContentContainerOwner owner = contentContainer.getOwner();
		if (owner instanceof BaseContentEntity) {
		    final BaseContentEntity baseContentEntity = (BaseContentEntity) owner;
		    baseContentEntity.setModifiedAt(new Date());
		}
	    }
	}
	return false;
    }

}
