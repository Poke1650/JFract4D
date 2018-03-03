package jfract4d.jfract.api.user;

/**
 *
 * @author Antoine Gagnon
 */
public interface User extends Comparable<User> {
    
    /**
     * @return Gets the role of the user
     * TODO: Users with multiple roles?
     */
    Role getRole();
    
    /**
     * @return an unique ID that identify this user
     */
    String getID();
}
