package jfract4d.discord.user;

import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.discord.util.FormatHelper;
import jfract4d.jfract.api.exception.MalformedIDException;
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.impl.RoleImpl;

/**
 * Implementation of a Role for Discord
 * @author Antoine Gagnon
 */
public class DiscordRole extends RoleImpl {

    public DiscordRole(String id, String name, int level) throws MalformedIDException {
        super(id, name, level);
    }

    @Override
    protected void setID(String id) throws MalformedIDException {
        if (!FormatHelper.isValidID(id)) {
            throw new MalformedDiscordIDException(id);
        }
        super.setID(id);
    }

    

}
