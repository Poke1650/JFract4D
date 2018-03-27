package jfract4d.discord.data;

import jfract4d.jfract.api.data.DataManager;
import jfract4d.jfract.api.data.InfractionManager;
import jfract4d.jfract.api.data.UserManager;

/**
 * Container for all data managers
 * @author Antoine Gagnon
 */
public class DiscordDataManager implements DataManager {

    /**
     * The infraction manager instance
     */
    private InfractionManager imanager;

    /**
     * The infraction manager instance
     */
    private UserManager umanager;

    /**
     * Create a new discord data manager and instantiate the other managers
     */
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
