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
public class PublishFlagInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = 6497532530213224372L;

    @Override
    public boolean onFlushDirty(final Object entity, final Serializable id, final Object[] currentState,
	    final Object[] previousState, final String[] propertyNames, final Type[] types) {

	if (entity instanceof BaseContentEntity) {
	    return setPublishFlagForEntity((BaseContentEntity) entity);
	} else if (entity instanceof Content) {
	    final ContentContainer contentContainer = ((Content) entity).getContainer();

	    // The content container is set to null if the content is
	    // unpublished. If this is the case we can use the
	    // previous value to retrieve the owner.
	    if (contentContainer == null) {
		for (int i = 0; i < propertyNames.length; i++) {
		    if (propertyNames[i].equals("container")) {
			setPublishFlagForContentContainer((ContentContainer) previousState[i], false);
			break;
		    }
		}
	    } else {
		final ContentContainerOwner owner = contentContainer.getOwner();
		if (owner instanceof BaseContentEntity) {
		    // Don't return true here even if the owner object has
		    // changed. It will be dealt with in a separate
		    // flush-call.
		    setPublishFlagForEntity((BaseContentEntity) owner);
		}
	    }
	} else if (entity instanceof ContentContainer) {
	    final ContentContainerOwner owner = ((ContentContainer) entity).getOwner();
	    if (owner instanceof BaseContentEntity) {
		// Don't return true here even if the owner object has changed.
		// It will be dealt with in a separate
		// flush-call.
		setPublishFlagForEntity((BaseContentEntity) owner);
	    }
	}
	return false;
    }

    private boolean setPublishFlagForContentContainer(final ContentContainer contentContainer,
	    final boolean publishFlag) {
	if (contentContainer != null) {
	    final ContentContainerOwner owner = contentContainer.getOwner();
	    if (owner instanceof BaseContentEntity) {
		return setPublishFlagForEntity((BaseContentEntity) owner, publishFlag);
	    }
	}
	return false;
    }

    private boolean setPublishFlagForEntity(final BaseContentEntity entity) {
	final Boolean publishFlag = entity.isPublishFlag();
	final boolean published = entity.isPublished();

	// Set the publish flag if it has not previously been set or if the
	// publish state is inconsistent.
	if (publishFlag == null || publishFlag != published) {
	    return setPublishFlagForEntity(entity, published);
	}

	return false;
    }

    private boolean setPublishFlagForEntity(final BaseContentEntity entity, final boolean publishFlag) {
	if (publishFlag && entity.getFirstPublishedAt() == null) {
	    entity.setFirstPublishedAt(new Date());
	}

	entity.setPublishFlag(publishFlag);
	entity.save();

	// True means that the entity has changed
	return true;
    }
}