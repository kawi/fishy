package se.report.fish.common.domain.listener;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.riotfamily.components.model.Content;
import org.riotfamily.components.model.ContentContainer;
import org.riotfamily.components.model.ContentContainerOwner;
import org.riotfamily.media.model.RiotImage;
import org.springframework.stereotype.Component;

import se.report.fish.common.domain.model.BaseContentEntity;
import se.report.fish.common.domain.model.Pet;

@Component
public class SaveEntityInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = -8904671469141407925L;

    private void handleImage(final ContentContainerOwner owner) {
	final RiotImage image = ((Pet) owner).getImage();
	if (image != null && image.getFileName() == null) {
	    image.setFileName("unnamed file");
	}
    }

    private void handleModifiedAt(final ContentContainerOwner owner) {
	final BaseContentEntity baseContentEntity = (BaseContentEntity) owner;
	baseContentEntity.setModifiedAt(new Date());
    }

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
		    handleModifiedAt(owner);
		}
		if (owner instanceof Pet) {
		    handleImage(owner);
		}
	    }
	}
	return false;
    }

}
