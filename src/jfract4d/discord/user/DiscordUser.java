package jfract4d.discord.user;

import jfract4d.discord.util.FormatHelper;
import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.User;

/**
 *
 * @author Antoine Gagnon
 */
public class DiscordUser implements User {

    /**
     * Role of the user
     */
    private Role role;

    /**
     * ID of the user, 18 char numeric string
     */
    private String id;

    public DiscordUser(String discordID, Role role) throws MalformedDiscordIDException {
        setID(discordID);
        setRole(role);
    }

    public DiscordUser(String discordID) throws MalformedDiscordIDException {
        setID(discordID);
    }

    private void setID(String discordID) throws MalformedDiscordIDException {
        if (!FormatHelper.isValidID(discordID)) {
            throw new MalformedDiscordIDException(discordID);
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
        return String.format("User ID: %s  |  Role: %s", getID(), getRole().toString());
    }
    
    

}
