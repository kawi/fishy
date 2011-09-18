package se.report.fish.common.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.riotfamily.common.web.cache.TagCacheItems;
import org.riotfamily.media.model.RiotImage;

@Entity
@TagCacheItems
@Table(name = "pets")
public class Pet extends BaseContentEntity {

    /**
     * FIXME: THIS CLASS IS JUST A TEST FIXME: Caused by:
     * com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column
     * 'title' in 'order clause'
     * 
     * @param id
     * @return
     */
    public static Pet load(final Long id) {
	return load(Pet.class, id);
    }

    public static List<Pet> loadAll() {
	return query(Pet.class, "from {}").find();
    }

    private RiotImage image;

    private String name;

    protected Pet() {
	super(Pet.class);
    }

    @ManyToOne
    @Cascade(CascadeType.ALL)
    public RiotImage getImage() {
	return this.image;
    }

    public String getName() {
	return this.name;
    }

    public void setImage(final RiotImage image) {
	this.image = image;
	// Fixme: Bad way of setting a filename
	this.image.setFileName("myFileName");
    }

    public void setName(final String name) {
	this.name = name;
    }

}
