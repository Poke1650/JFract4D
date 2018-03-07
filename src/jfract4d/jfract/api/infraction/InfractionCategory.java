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

    @Override
    public default int compareTo(InfractionCategory o) {
        return getPoints() > o.getPoints() ? 1 : getPoints() < o.getPoints() ? -1 : 0;
    }  
    
    /**
     * 
     * @return the id of this infraction category
     */
    int getID();

}
