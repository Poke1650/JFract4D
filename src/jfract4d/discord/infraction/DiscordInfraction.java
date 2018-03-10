package jfract4d.discord.infraction;

import java.sql.SQLException;
import java.util.Date;
import jfract4d.jfract.api.infraction.Infraction;
import jfract4d.jfract.api.infraction.InfractionType;
import jfract4d.jfract.api.user.User;
import jfract4d.jfract.helper.InfractionHelper;

/**
 *
 * @author Antoine Gagnon
 */
public class DiscordInfraction implements Infraction {

    /**
     * The ID of this infraction, a 8 character hex string
     */
    String id;

    /**
     * The user giving the infraction
     */
    User giver;

    /**
     * The target of that infraction
     */
    User target;

    /**
     * Type of infraction
     */
    InfractionType iType;

    /**
     * If this infraction is effective
     */
    boolean effective;

    /**
     * The time at which this infraction was given
     */
    Date time;

    /**
     * Construct an infraction for an already existing infraction You should
     * never instantiate this yourself
     *
     * @param id
     * @param giver
     * @param target
     * @param time
     * @param iType
     * @param effective
     */
    public DiscordInfraction(String id, User giver, User target, Date time, InfractionType iType, boolean effective) {
        this.id = id;
        this.giver = giver;
        this.target = target;
        this.effective = effective;
        this.time = time;
        this.iType = iType;
    }

    /**
     * Constructs a completely new infraction
     *
     * @param giver
     * @param target
     * @param time
     * @param iType
     * @throws java.sql.SQLException
     */
    public DiscordInfraction(User giver, User target, Date time, InfractionType iType) throws SQLException {

        this.id = InfractionHelper.getNextInfractionID();

        this.giver = giver;
        this.target = target;
        
        this.iType = iType;

        this.effective = true;
        this.time = time;
    }

    /**
     * Constructs a completely new infraction at the current time
     *
     * @param giver
     * @param target
     * @param iType
     * @throws java.sql.SQLException
     */
    public DiscordInfraction(User giver, User target, InfractionType iType) throws SQLException {

        this.id = InfractionHelper.getNextInfractionID();

        this.giver = giver;
        this.target = target;
        
        this.iType = iType;

        this.effective = true;
        this.time = new Date();
    }

    @Override
    public User getGiver() {
        return giver;
    }

    @Override
    public User getTarget() {
        return target;
    }

    @Override
    public InfractionType getType() {
        return iType;
    }

    @Override
    public Date getTime() {
        return time;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public boolean isEffective() {
        return effective;
    }

    @Override
    public String toString() {
        return id;
    }
    
    

}
