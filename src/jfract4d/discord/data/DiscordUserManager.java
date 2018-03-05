package jfract4d.discord.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jfract4d.jfract.JFract;
import jfract4d.jfract.api.data.UserManager;
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.User;

/**
 *
 * @author Antoine Gagnon
 */
public class DiscordUserManager implements UserManager {

    public DiscordUserManager() {
        
    }
    
    @Override
    public void addUser(User user) {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement;
            PreparedStatement stat;
                    
            if(user.getRole() == null) {
                statement = "INSERT INTO user (discord_id) VALUES (?)";
                stat = conn.prepareStatement(statement);
                
                stat.setString(1, user.getID());
            } else {
                statement = "INSERT INTO user VALUES (?,?)";
                stat = conn.prepareStatement(statement);
                
                stat.setString(1, user.getID());
                stat.setString(2, user.getRole().getID());
            }
            stat.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role getRoleForUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role getRoleForUser(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role getRole(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRole(Role role) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> getRoles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
