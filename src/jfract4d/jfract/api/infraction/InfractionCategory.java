package jfract4d.jfract.api.infraction;

/**
 * A category in which an {@link InfractionType} is. This is mostly to sort the
 * {@link InfractionType} by how serious it is as every categories has a point
 * value associated with it
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
