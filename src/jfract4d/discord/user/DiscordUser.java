package jfract4d.discord.user;

import jfract4d.discord.util.FormatHelper;
import jfract4d.jfract.api.exception.MalformedIDException;
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.impl.UserImpl;

/**
 * Implementation of a discord user
 * @author Antoine Gagnon
 */
public class DiscordUser extends UserImpl {

    public DiscordUser(String id, Role role) throws MalformedIDException {
        super(id, role);
    }
    
    public DiscordUser(String id) throws MalformedIDException {
        super(id);
    }    

    @Override
    protected void setID(String discordID) throws MalformedIDException {
        if (!FormatHelper.isValidID(discordID)) {
            throw new MalformedIDException(discordID);
        }
        super.setID(discordID);
    }    
}
