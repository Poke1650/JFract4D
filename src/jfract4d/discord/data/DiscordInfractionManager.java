package jfract4d.discord.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jfract4d.discord.infraction.DiscordInfractionCategory;
import jfract4d.jfract.JFract;

import jfract4d.jfract.api.data.InfractionManager;
import jfract4d.jfract.api.infraction.Infraction;
import jfract4d.jfract.api.infraction.InfractionCategory;
import jfract4d.jfract.api.infraction.InfractionType;
import jfract4d.jfract.api.user.User;

/**
 *
 * @author Antoine Gagnon
 */
public class DiscordInfractionManager implements InfractionManager {

    DiscordInfractionManager() {

    }

    @Override
    public void addInfractionCategory(InfractionCategory icategory) {

        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("INSERT INTO infraction_category (name, points) VALUES (?,?)");

            stat.setString(1, icategory.getName());
            stat.setInt(2, icategory.getPoints());

            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public InfractionCategory getInfractionCategory(String name) {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("SELECT * FROM infraction_category WHERE name = ?");

            stat.setString(1, name);

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                return new DiscordInfractionCategory(rs.getInt("id"), rs.getString("name"), rs.getInt("points"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public InfractionCategory getInfractionCategory(int id) {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("SELECT * FROM infraction_category WHERE id = ?");

            stat.setInt(1, id);

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                return new DiscordInfractionCategory(rs.getInt("id"), rs.getString("name"), rs.getInt("points"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<InfractionCategory> getInfractionCategories() {

        List<InfractionCategory> list = new ArrayList<>();
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("SELECT * FROM infraction_category");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                list.add(new DiscordInfractionCategory(rs.getInt("id"), rs.getString("name"), rs.getInt("points")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void updateInfractionCategory(String name, InfractionCategory updatedICategory) {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "UPDATE infraction_category SET points = ? WHERE name = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            
            stat.setInt(1, updatedICategory.getPoints());
            stat.setString(2, name);

            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeInfractionCategory(String name) {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "DELETE FROM infraction_category WHERE name = ?";
            PreparedStatement stat = conn.prepareStatement(statement);
            
            stat.setString(1, name);
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addInfractionType(InfractionType itype) {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "INSERT INTO infraction_type (name, description, catgegory) VALUES (?,?,?)";
            PreparedStatement stat = conn.prepareStatement(statement);
            
            stat.setString(1, itype.getName());
            stat.setString(2, itype.getDescription());
            stat.setInt(3, itype.getCategory().getID());
            
            stat.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DiscordInfractionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public InfractionType getInfractionType(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InfractionType> getInfractionTypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateInfractionType(String name, InfractionType updatedIType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeInfractionType(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addInfraction(Infraction infraction) {

        //stat.setTimetamp(1, DateHelper.toTimestamp(infraction.getTime())

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Infraction getInfraction(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Infraction> getInfractionByUser(User giver) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Infraction> getInfractionByGiver(User target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Infraction> getInfractionBy(User giver, User target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Infraction> getInfractionsBetween(Date start, Date end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Infraction> getAllInfractions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateInfraction(String id, Infraction updatedInfraction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeInfraction(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateInfractionEffective(String id, boolean effective) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
