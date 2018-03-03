package jfract4d.jfract.api.infraction;

import java.util.Date;
import jfract4d.jfract.api.user.User;

/**
 *
 * @author Antoine Gagnon
 */
public interface Infraction {
    
    /**
     * @return the user that gave this infraction
     */
    User getGiver();
    
    /**
     * @return the user that was given this infraction
     */
    User getTarget();
    
    
    /**
     * @return the type of the infraction
     */
    InfractionType getType();
    
    /**
     * @return the time at which this infraction was given 
     */
    Date getTime();
}
