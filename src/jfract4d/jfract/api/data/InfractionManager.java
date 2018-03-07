/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.jfract.api.data;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import jfract4d.jfract.api.infraction.Infraction;
import jfract4d.jfract.api.infraction.InfractionCategory;
import jfract4d.jfract.api.infraction.InfractionType;
import jfract4d.jfract.api.user.User;

/**
 *
 * @author Antoine Gagnon
 */
public interface InfractionManager {

    /* INFRACTION CATEGORIES */
    /**
     * Adds an infraction category to the database
     *
     * @param icaterogy
     * @throws java.sql.SQLException
     */
    void addInfractionCategory(InfractionCategory icaterogy) throws SQLException ;

    /**
     * Gets an infraction category by its name
     *
     * @param name
     * @return InfractionCategory by the name passed
     * @throws java.sql.SQLException
     */
    InfractionCategory getInfractionCategory(String name) throws SQLException;

    /**
     * @return all the infraction categories
     * @throws java.sql.SQLException
     */
    List<InfractionCategory> getInfractionCategories() throws SQLException;

    /**
     * Updates an infraction by its name
     *
     * @param name
     * @param updatedICategory
     * @throws java.sql.SQLException
     */
    void updateInfractionCategory(String name, InfractionCategory updatedICategory) throws SQLException;

    /**
     * Remove an infraction All infraction types going by that category will be
     * moved to the UNDEFINED category
     *
     * @param name
     * @throws java.sql.SQLException
     */
    void removeInfractionCategory(String name) throws SQLException;

    /* INFRACTION TYPES */
    /**
     * Adds an infraction type
     *
     * @param itype
     * @throws java.sql.SQLException
     */
    void addInfractionType(InfractionType itype) throws SQLException;

    /**
     * Gets an infraction type by its name
     *
     * @param name
     * @return an infraction type that goes by the name given
     * @throws java.sql.SQLException
     */
    InfractionType getInfractionType(String name) throws SQLException;

    /**
     * @return every infraction type in the database
     * @throws java.sql.SQLException
     */
    List<InfractionType> getInfractionTypes() throws SQLException;

    /**
     * Updates the infraction type going by the name given from the updateIType
     * passed
     *
     * @param name
     * @param updatedIType
     * @throws java.sql.SQLException
     */
    void updateInfractionType(String name, InfractionType updatedIType) throws SQLException;

    /**
     * Removes an infraction type this will cause every infraction going by that
     * type to UNDEFINED
     *
     * @param name
     * @throws java.sql.SQLException
     */
    void removeInfractionType(String name) throws SQLException;

    /* INFRACTIONS */
    /**
     * Adds an infraction
     *
     * @param infraction
     * @throws java.sql.SQLException
     */
    void addInfraction(Infraction infraction) throws SQLException;

    /**
     * Gets an infraction by its ID
     *
     * @param id
     * @return an infraction by the ID given
     * @throws java.sql.SQLException
     */
    Infraction getInfraction(String id) throws SQLException;

    /**
     * Gets the infraction(s) by the giver
     *
     * @param giver
     * @return all the infraction given by the user
     * @throws java.sql.SQLException
     */
    List<Infraction> getInfractionByUser(User giver) throws SQLException;

    /**
     * Gets the infraction(s) by the target
     *
     * @param target
     * @return all the infraction given to the user
     * @throws java.sql.SQLException
     */
    List<Infraction> getInfractionByGiver(User target) throws SQLException;

    /**
     * Gets the infraction given by the giver to the target
     *
     * @param giver
     * @param target
     * @return every infraction given by giver to target
     * @throws java.sql.SQLException
     */
    List<Infraction> getInfractionBy(User giver, User target) throws SQLException;

    /**
     * Gets the infraction given between a specified time
     *
     * @param start
     * @param end
     * @return every infractions given in the given time
     * @throws java.sql.SQLException
     */
    List<Infraction> getInfractionsBetween(Date start, Date end) throws SQLException;

    /**
     * Gets all infractions
     *
     * @return every infractions
     * @throws java.sql.SQLException
     */
    List<Infraction> getAllInfractions() throws SQLException;

    /**
     * Updates an infraction given its ID by the infraction passed
     *
     * @param id
     * @param updatedInfraction
     * @throws java.sql.SQLException
     */
    void updateInfraction(String id, Infraction updatedInfraction) throws SQLException;

    /**
     * Removes an infraction by its ID This will remove every trace of that
     * infraction ever being given If you want to pardon a user but keep the
     * infraction record, see
     * {@link InfractionManager#updateInfractionEffective(java.lang.String, boolean)}
     *
     * @param id
     * @throws java.sql.SQLException
     */
    void removeInfraction(String id) throws SQLException;

    /**
     * Changes the effectivness of an infraction
     *
     * @param id
     * @param effective
     * @throws java.sql.SQLException
     */
    void updateInfractionEffective(String id, boolean effective) throws SQLException;
}
