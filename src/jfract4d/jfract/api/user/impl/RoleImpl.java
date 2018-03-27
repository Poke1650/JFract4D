package jfract4d.jfract.api.user.impl;

import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.discord.util.FormatHelper;
import jfract4d.jfract.api.exception.MalformedIDException;
import jfract4d.jfract.api.user.Role;

/**
 *
 * @author Antoine Gagnon
 */
public class RoleImpl implements Role{
  /**
     * Name of the role
     */
    String name;
    
    /**
     * Permission level of this role
     */
    int level;
    
    /**
     * the ID of the role, this SHOULD be unique for each role
     */
    String id;

    /**
     * Create a new discord role
     * @param id
     * @param name
     * @param level
     * @throws MalformedDiscordIDException 
     */
    public RoleImpl(String id, String name, int level) throws MalformedIDException {
        this.name = name;
        this.level = level;
        setID(id);
    }
    
    /**
     * Sets the ID of the role
     * Override to add your own rules
     * @param id
     * @throws MalformedIDException 
     */
    protected void setID(String id) throws MalformedIDException {
        this.id = id;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int compareTo(Role o) {
        return getLevel() > o.getLevel() ? 1 : getLevel() < o.getLevel() ? -1 : 0;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Name: %s LvL: %s ID: %s", getName(), getLevel(), getID());
    }

}
