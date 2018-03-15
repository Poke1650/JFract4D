package jfract4d.jfract.api.infraction;

import java.util.Date;
import jfract4d.jfract.api.user.User;

/**
 * An Infraction record given to someone by someone
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

    /**
     * @return the ID of the infraction, a 8 character HEX string
     */
    String getID();

    /**
     * @return if the infraction is still in effect or was pardonned
     */
    boolean isEffective();
}
