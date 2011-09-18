package se.report.fish.common.domain.listener;

import java.io.File;
import java.io.IOException;

import org.hibernate.Session;
import org.riotfamily.common.hibernate.EntityListener;
import org.riotfamily.media.model.RiotImage;
import org.riotfamily.media.processing.Thumbnailer;

import se.report.fish.common.domain.model.Pet;

//import org.riotfamily.common.image.Thumbnailer;
//import org.riotfamily.riot.hibernate.interceptor.EntityListener;
public class PetListener implements EntityListener {

    private final Thumbnailer thumbnailer;

    public PetListener(final Thumbnailer thumbnailer) {
	this.thumbnailer = thumbnailer;
    }

    private RiotImage createThumbnail(final RiotImage img) throws IOException {
	final RiotImage thumb = new RiotImage();
	final File source = img.getFile();
	final File dest = thumb.createEmptyFile(img.getFileName());
	this.thumbnailer.renderThumbnail(source, dest, 80, 80, true, null);
	thumb.save();
	return thumb;
    }

    public void onDelete(final Object entity, final Session session) throws Exception {
	// TODO Auto-generated method stub

    }

    public void onDelete(final Pet pet) {
    }

    public void onSave(final Object entity, final Session session) throws Exception {
	// TODO Auto-generated method stub

    }

    public void onSave(final Pet pet) throws IOException {
	updateThumbnail(pet);
    }

    public void onUpdate(final Object entity, final Object oldState, final Session session) throws Exception {
	// TODO Auto-generated method stub

    }

    public void onUpdate(final Pet pet) throws IOException {
	updateThumbnail(pet);
    }

    public boolean supports(final Class<?> entityClass) {
	// TODO Auto-generated method stub
	return false;
    }

    private void updateThumbnail(final Pet pet) throws IOException {
	final RiotImage img = pet.getImage();
	if (img != null) {
	    if (img.get("thumbnail") == null) {
		img.addVariant("thumbnail", createThumbnail(img));
	    }
	}
    }
}
