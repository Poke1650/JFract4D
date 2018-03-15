package jfract4d.jfract.api.user;

/**
 * The Role of a user in the community or server, can be used to establish a
 * hierarchy between the users and staff members
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

    /**
     *
     * @return the ID of the role, 18 char
     */
    String getID();
}
