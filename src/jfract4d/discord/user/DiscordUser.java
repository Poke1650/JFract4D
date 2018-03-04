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

    private Role role;

    private final String id;

    public DiscordUser(String discordID, Role role) throws MalformedDiscordIDException {
        if (!FormatHelper.isValidID(discordID)) {
            throw new MalformedDiscordIDException(discordID);
        }

        this.id = discordID;
        setRole(role);
    }

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
        return getRole().compareTo(o.getRole());
    }

}
