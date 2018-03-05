package jfract4d.discord.user;

import jfract4d.jfract.api.user.Role;

/**
 *
 * @author Antoine Gagnon
 */
public class DiscordRole implements Role {

    String name;
    int level;
    String id;
    
    
    public DiscordRole(String id, String name, int level) {
        this.name = name;
        this.level = level;
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
    
}
