package jfract4d.discord.infraction;

import java.util.Date;
import jfract4d.discord.user.DiscordUser;
import jfract4d.discord.util.FormatHelper;
import jfract4d.jfract.api.infraction.Infraction;
import jfract4d.jfract.api.infraction.InfractionType;
import jfract4d.jfract.api.user.User;

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
    DiscordUser giver;

    /**
     * The target of that infraction
     */
    DiscordUser target;

    /**
     * Type of infraction
     */
    DiscordInfraction iType;

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
     * @param effective
     */
    public DiscordInfraction(String id, DiscordUser giver, DiscordUser target, Date time, boolean effective) {
        this.id = id;
        this.giver = giver;
        this.target = target;
        this.effective = effective;
        this.time = time;
    }

    /**
     * Constructs a completely new infraction
     *
     * @param giver
     * @param target
     * @param time
     */
    public DiscordInfraction(DiscordUser giver, DiscordUser target, Date time) {

        this.id = FormatHelper.generateInfractionID();

        this.giver = giver;
        this.target = target;

        this.effective = true;
        this.time = time;
    }

    /**
     * Constructs a completely new infraction at the current time
     *
     * @param giver
     * @param target
     */
    public DiscordInfraction(DiscordUser giver, DiscordUser target) {

        this.id = FormatHelper.generateInfractionID();

        this.giver = giver;
        this.target = target;

        this.effective = true;
        this.time = new Date();
    }

    @Override
    public User getGiver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getTarget() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public InfractionType getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEffective() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
