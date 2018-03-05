package jfract4d.discord.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<InfractionCategory> getInfractionCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateInfractionCategory(String name, InfractionCategory updatedICategory) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeInfractionCategory(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addInfractionType(InfractionType itype) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
