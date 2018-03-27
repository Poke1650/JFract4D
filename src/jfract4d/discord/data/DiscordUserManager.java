package jfract4d.discord.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.discord.user.DiscordRole;
import jfract4d.discord.user.DiscordUser;
import jfract4d.jfract.JFract;
import jfract4d.jfract.api.data.UserManager;
import jfract4d.jfract.api.exception.MalformedIDException;
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.User;

/**
 * User manager implementation for Discord users
 * @author Antoine Gagnon
 */
public class DiscordUserManager implements UserManager {

    public DiscordUserManager() {

    }

    @Override
    public void addUser(User user) throws SQLException {
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

        }
    }

    @Override
    public void addUsers(List<User> users) throws SQLException {

        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "INSERT INTO user VALUES (?,?)";;
            PreparedStatement stat = conn.prepareStatement(statement);

            for (User user : users) {
                stat.setString(1, user.getID());
                if (user.getRole() == null) {
                    stat.setNull(2, Types.CHAR);
                } else {
                    stat.setString(2, user.getRole().getID());
                }
                stat.addBatch();
            }

            if (stat != null) {
                stat.executeBatch();
            }
        }
    }

    @Override
    public void removeUser(String id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "DELETE FROM user WHERE discord_id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, id);
            stat.executeUpdate();
        }
    }

    @Override
    public void updateUserRole(String user_id, Role newRole) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "UPDATE user SET role = ? WHERE discord_id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, newRole.getID());
            stat.setString(2, user_id);
            stat.executeUpdate();

        }
    }

    @Override
    public List<User> getUsers() throws SQLException {

        ArrayList<User> list = new ArrayList<>();

        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "SELECT * FROM user LEFT JOIN role ON user.role = role.id";
            PreparedStatement stat = conn.prepareStatement(statement);

            ResultSet result = stat.executeQuery();

            while (result.next()) {
                try {

                    DiscordUser usr = new DiscordUser(result.getString("discord_id"));

                    String role = result.getString("id");
                    if (!result.wasNull()) {
                        usr.setRole(new DiscordRole(role, result.getString("name"), result.getInt("level")));
                    }

                    list.add(usr);
                } catch (MalformedIDException ex) {
                    Logger.getLogger(DiscordUserManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return list;
    }

    @Override
    public Role getRoleForUser(User user) throws SQLException {
        return getRoleForUser(user.getID());
    }

    @Override
    public Role getRoleForUser(String id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "SELECT role.* FROM user INNER JOIN role ON user.role = role.id WHERE user.discord_id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, id);
            ResultSet result = stat.executeQuery();

            if (result.next()) {
                return new DiscordRole(result.getString("id"), result.getString("name"), result.getInt("level"));
            }
        } catch (MalformedIDException ex) {
            Logger.getLogger(DiscordUserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Role getRole(String id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "SELECT * FROM role WHERE id = (?)";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, id);
            ResultSet result = stat.executeQuery();

            if (result.next()) {
                return new DiscordRole(result.getString("id"), result.getString("name"), result.getInt("level"));
            }

        } catch (MalformedIDException ex) {
            Logger.getLogger(DiscordUserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void addRole(Role role) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "INSERT INTO role VALUES (?, ?, ?)";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, role.getID());
            stat.setString(2, role.getName());
            stat.setInt(3, role.getLevel());

            stat.executeUpdate();

        }
    }

    @Override
    public void updateRole(String id, Role role) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "UPDATE role SET name = ?, level = ? WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, role.getName());
            stat.setInt(2, role.getLevel());
            stat.setString(3, id);

            stat.executeUpdate();

        }
    }

    @Override
    public List<Role> getRoles() throws SQLException {
        ArrayList<Role> list = new ArrayList<>();

        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "SELECT * FROM role ORDER BY level DESC";
            PreparedStatement stat = conn.prepareStatement(statement);

            ResultSet result = stat.executeQuery();

            while (result.next()) {
                list.add(new DiscordRole(result.getString("id"), result.getString("name"), result.getInt("level")));
            }

        } catch (MalformedIDException ex) {
            Logger.getLogger(DiscordUserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void removeRole(String id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "DELETE FROM role WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, id);
            stat.executeUpdate();
        }
    }
}
