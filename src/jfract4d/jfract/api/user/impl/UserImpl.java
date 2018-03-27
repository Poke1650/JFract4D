package jfract4d.jfract.api.user.impl;

import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.discord.util.FormatHelper;
import jfract4d.jfract.api.exception.MalformedIDException;
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.User;

/**
 * Basic implementation of a user 
 * @author Antoine Gagnon
 */
public class UserImpl implements User {
    
     /**
     * Role of the user
     */
    protected Role role;

    /**
     * ID of the user, 18 char numeric string
     */
    protected String id;

    public UserImpl(String id, Role role) throws MalformedIDException {
        setID(id);
        setRole(role);
    }

    public UserImpl(String id) throws MalformedIDException {
        setID(id);
    }
    
    /**
     * Verify and set the ID
     * Override this to set your own rules
     * @param discordID
     * @throws MalformedDiscordIDException 
     */
    protected void setID(String discordID) throws MalformedIDException {
        
        this.id = id;
        
        if (!FormatHelper.isValidID(discordID)) {
            throw new MalformedIDException(discordID);
        }

        this.id = discordID;
    }

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Role getRole() {
        return this.role;
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public int compareTo(User o) {

        return o.getRole() == null && this.role == null ? 0
                : o.getRole() != null && this.role == null ? -1
                : o.getRole() == null && this.role != null ? 0
                : this.role.compareTo(o.getRole());
    }

    @Override
    public String toString() {
        return String.format("%s  |  Role: %s", getID(), getRole().getName());
    }
}
