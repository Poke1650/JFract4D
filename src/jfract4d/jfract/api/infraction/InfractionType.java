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
     * @return the category in which this infraction is
     */
    InfractionCategory getCategory();
}
