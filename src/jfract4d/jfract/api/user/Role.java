package jfract4d.jfract.api.user;

/**
 *
 * @author Antoine Gagnon
 */
public interface Role extends Comparable<Role> {

    /**
     * @return The name of this role
     */
    String getName();

    /**
     * @return the administrative level of the role
     */
    int getLevel();
}
