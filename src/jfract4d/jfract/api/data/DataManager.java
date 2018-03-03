package jfract4d.jfract.api.data;

import jfract4d.jfract.api.infraction.Infraction;
import jfract4d.jfract.api.infraction.InfractionCategory;
import jfract4d.jfract.api.infraction.InfractionType;
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.User;

/**
 * @author Antoine Gagnon
 */
public interface DataManager {

    void addInfractionType(InfractionType itype);
    
    void addInfractionCategory(InfractionCategory icaterogy);
    
    void addInfraction(Infraction infraction);
    
    void addUser(User user);
    
    void addRole(Role role);
}
