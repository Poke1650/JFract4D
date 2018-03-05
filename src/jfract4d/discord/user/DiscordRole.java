package jfract4d.discord.user;

import jfract4d.jfract.api.user.Role;

/**
 *
 * @author Antoine Gagnon
 */
public class DiscordRole implements Role {

    String name;
    int level;
    
    public DiscordRole(String name, int level) {
        this.name = name;
        this.level = level;
    }
    
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Role o) {
        return getLevel() > o.getLevel() ? 1 : getLevel() < o.getLevel() ? -1 : 0;
    }

    @Override
    public String getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
