package se.report.fish.common.domain.model;

import java.util.Date;

public interface Publishable {
    /**
     * 
     * @return
     */
    Date getPublishAt();

    /**
     * 
     * @return
     */
    Date getUnpublishAt();

    /**
     * Returns true if the entity has been published but not modified after the
     * publish.
     * 
     * @return True if not dirty
     */
    boolean isDirty();

    /**
     * Returns true if the entity has been published
     * 
     * @return true if published
     */
    boolean isPublished();

    /**
     * Publishes the entity
     */
    void publish();

    /**
     * Unpublishes the entity
     */

    void unpublish();
}
