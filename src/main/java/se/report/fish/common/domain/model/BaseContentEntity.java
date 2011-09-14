package se.report.fish.common.domain.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.riotfamily.components.model.Component;
import org.riotfamily.components.model.ContentEntity;
import org.springframework.util.Assert;

/**
 * Adds a layer with convenience methods between the Riot ContentEntity and
 * concrete application subclasses.
 */
@MappedSuperclass
public abstract class BaseContentEntity extends ContentEntity implements Publishable, Resolveable, Saveable {

    private final Class<? extends BaseContentEntity> actualClass;

    private Date createdAt;

    private Date firstPublishedAt;

    private Long id;

    private Date modifiedAt;
    private Date publishAt;

    /**
     * Indicator if this entity is published or not. Note that the
     * {@link #isPublished()}-method should be used in the code to determine
     * this. This attribute is mainly used for simplified querying.
     */
    private Boolean publishFlag = Boolean.FALSE;

    private String title;

    private Date unpublishAt;

    /**
     * Creates the base entity with the subclass <code>Class</code>-object as
     * argument to get the correct class names even if the actual implementation
     * is a proxy.
     * 
     * @param actualClass
     *            The subclass <code>Class</code>.
     */
    protected BaseContentEntity(final Class<? extends BaseContentEntity> actualClass) {

	// Set the create date/modify to now - will be overwritten if read from
	// db
	this.createdAt = new Date();
	this.modifiedAt = new Date();

	// setFirstPublishedAt is null until the entity is published
	setFirstPublishedAt(null);

	Assert.notNull(actualClass, "The provided class object must not be null");
	this.actualClass = actualClass;
    }

    @Transient
    public Class<? extends BaseContentEntity> getActualClass() {
	return this.actualClass;
    }

    @Transient
    public String getClassName() {
	return this.actualClass.getSimpleName();
    }

    /**
     * Gets a list of all components related to this entity (published version).
     */
    @SuppressWarnings("unchecked")
    public Collection<Component> getComponents(final String componentList) {
	return (Collection<Component>) getField(componentList);
    }

    /**
     * Gets a list of all components related to this entity (preview version).
     */
    @SuppressWarnings("unchecked")
    public Collection<Component> getComponentsPreview(final String componentList) {
	return (Collection<Component>) getFieldPreview(componentList);
    }

    /**
     * Returns a "content field" as a String, from the published version of the
     * content.
     */
    @Transient
    public String getContentString(final String fieldName) {
	return (String) getField(fieldName);
    }

    /**
     * Returns a "content field" as a String, from the preview version of the
     * content.
     */
    @Transient
    public String getContentStringPreview(final String fieldName) {
	return (String) getFieldPreview(fieldName);
    }

    public Date getCreatedAt() {
	return this.createdAt;
    }

    /**
     * Gets an untyped field by name (published version).
     */
    public Object getField(final String fieldName) {
	return getContentContainer().getContent(false).get(fieldName);
    }

    /**
     * Gets an untyped field by name (preview version).
     */
    public Object getFieldPreview(final String fieldName) {
	return getContentContainer().getContent(true).get(fieldName);
    }

    /**
     * Returns all content field names, from the published version of the
     * content.
     */
    @Transient
    public Collection<String> getFields() {
	return getContentContainer().getContent(false).keySet();
    }

    /**
     * Returns all content field names, from the preview version of the content.
     */
    @Transient
    public Collection<String> getFieldsPreview() {
	return getContentContainer().getContent(true).keySet();
    }

    /**
     * The date the entity was first published.
     */
    public Date getFirstPublishedAt() {
	return this.firstPublishedAt;
    }

    @Transient
    public String getFullyQualifiedClassName() {
	return this.actualClass.getName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
	return this.id;
    }

    public Date getModifiedAt() {
	return this.modifiedAt;
    }

    public Date getPublishAt() {
	return this.publishAt;
    }

    public String getTitle() {
	return this.title;
    }

    public Date getUnpublishAt() {
	return this.unpublishAt;
    }

    @Transient
    public boolean isDirty() {
	return getContentContainer().isDirty();
    }

    /**
     * Convenience attribute to easier make select-queries. This method should
     * not be used directly, instead use {@link #isPublished()}.
     * 
     * @return True if this object is published.
     * @see #isPublished()
     */
    public Boolean isPublishFlag() {
	return this.publishFlag;
    }

    public void publish() {
	setPublishFlag(true);
	getContentContainer().publish();
    }

    public void setCreatedAt(final Date createdAt) {
	this.createdAt = createdAt;
    }

    /**
     * To be set the first time a entity is published and then never again.
     * 
     * @param firstPublishedAt
     *            The date when it was first published.
     */
    public void setFirstPublishedAt(final Date firstPublishedAt) {
	this.firstPublishedAt = firstPublishedAt;
    }

    public void setId(final Long id) {
	this.id = id;
    }

    public void setModifiedAt(final Date modifiedAt) {
	this.modifiedAt = modifiedAt;
    }

    public void setPublishAt(final Date publishAt) {
	this.publishAt = publishAt;
    }

    /**
     * Indicator if this entity is published or not. Note that the
     * {@link #isPublished()}-method should be used in the code to determine
     * this. This attribute is mainly used for simplified querying.
     */
    public void setPublishFlag(final Boolean publishFlag) {
	this.publishFlag = publishFlag;
    }

    public void setTitle(final String title) {
	this.title = title;
    }

    public void setUnpublishAt(final Date unpublishAt) {
	this.unpublishAt = unpublishAt;
    }

    public void unpublish() {
	getContentContainer().unpublish();
    }

}
