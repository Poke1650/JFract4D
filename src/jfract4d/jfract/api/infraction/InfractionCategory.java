package jfract4d.jfract.api.infraction;

/**
 *
 * @author Antoine Gagnon
 */
public interface InfractionCategory extends Comparable<InfractionCategory> {

    /**
     * @return the name of the category
     */
    String getName();

    /**
     * @return the point value of an infraction in that category
     */
    int getPoints();

}
