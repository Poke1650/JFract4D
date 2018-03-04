package jfract4d.jfract.api.infraction;

/**
 *
 * @author Antoine Gagnon
 */
public interface InfractionType {

    /**
     * @return the name of this type of infraction
     */
    String getName();

    /**
     * @return description of this infraction
     */
    String getDescription();

    /**
     * Sets the description of the category
     *
     * @param description
     */
    void setDescription(String description);

    /**
     * Sets the category in which this infraction type is
     *
     * @param iCategory
     */
    void setCategory(InfractionCategory iCategory);

    /**
     * @return the category in which this infraction is
     */
    InfractionCategory getCategory();
}