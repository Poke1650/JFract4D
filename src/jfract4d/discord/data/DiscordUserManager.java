package jfract4d.discord.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.discord.user.DiscordRole;
import jfract4d.discord.user.DiscordUser;
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

            if (user.getRole() == null) {
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
    public void removeUser(String id) {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement;
            PreparedStatement stat;

            statement = "DELETE FROM user WHERE discord_id = (?)";
            stat = conn.prepareStatement(statement);

            stat.setString(1, id);
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getUsers() {

        ArrayList<User> list = new ArrayList<>();

        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement;
            PreparedStatement stat;

            statement = "SELECT * FROM user LEFT JOIN role ON user.role = role.id";
            stat = conn.prepareStatement(statement);

            ResultSet result = stat.executeQuery();

            while (result.next()) {
                try {

                    DiscordUser usr = new DiscordUser(result.getString("discord_id"));

                    String role = result.getString("id");
                    if (!result.wasNull()) {
                        usr.setRole(new DiscordRole(role, result.getString("name"), result.getInt("level")));
                    }

                    list.add(usr);
                } catch (MalformedDiscordIDException ex) {
                    Logger.getLogger(DiscordUserManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Role getRoleForUser(User user) {
        return getRoleForUser(user.getID());
    }

    @Override
    public Role getRoleForUser(String id) {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement;
            PreparedStatement stat;

            statement = "SELECT * FROM role WHERE id = (SELECT role FROM user WHERE discord_id = ?)";
            stat = conn.prepareStatement(statement);

            stat.setString(1, id);
            ResultSet result = stat.executeQuery();

            if (result.next()) {
                return new DiscordRole(result.getString("id"), result.getString("name"), result.getInt("level"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
