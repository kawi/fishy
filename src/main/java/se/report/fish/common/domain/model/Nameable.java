package se.report.fish.common.domain.model;

public interface Nameable extends Identifiable {
    /**
     * Gets the title of this named object.
     * 
     * @return The title.
     */
    String getTitle();
}
