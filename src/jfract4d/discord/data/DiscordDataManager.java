package jfract4d.discord.data;

import jfract4d.jfract.api.data.DataManager;
import jfract4d.jfract.api.data.InfractionManager;
import jfract4d.jfract.api.data.UserManager;

/**
 *
 * @author Antoine Gagnon
 */
public class DiscordDataManager implements DataManager {
    
    private InfractionManager imanager;
    
    private UserManager umanager;
    
    public DiscordDataManager() {
        imanager = new DiscordInfractionManager();
        umanager = new DiscordUserManager();
    }

    @Override
    public InfractionManager getInfractionManager() {
        return imanager;
    }

    @Override
    public UserManager getUserManager() {
        return umanager;
    }
}
