/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.jfract.api.data;

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
     */
    void addInfractionCategory(InfractionCategory icaterogy);

    /**
     * Gets an infraction category by its name
     *
     * @param name
     * @return InfractionCategory by the name passed
     */
    InfractionCategory getInfractionCategory(String name);

    /**
     * @return all the infraction categories
     */
    List<InfractionCategory> getInfractionCategories();

    /**
     * Updates an infraction by its name
     *
     * @param name
     * @param updatedICategory
     */
    void updateInfractionCategory(String name, InfractionCategory updatedICategory);

    /**
     * Remove an infraction All infraction types going by that category will be
     * moved to the UNDEFINED category
     *
     * @param name
     */
    void removeInfractionCategory(String name);

    /* INFRACTION TYPES */
    /**
     * Adds an infraction type
     *
     * @param itype
     */
    void addInfractionType(InfractionType itype);

    /**
     * Gets an infraction type by its name
     *
     * @param name
     * @return an infraction type that goes by the name given
     */
    InfractionType getInfractionType(String name);

    /**
     * @return every infraction type in the database
     */
    List<InfractionType> getInfractionTypes();

    /**
     * Updates the infraction type going by the name given from the updateIType
     * passed
     *
     * @param name
     * @param updatedIType
     */
    void updateInfractionType(String name, InfractionType updatedIType);

    /**
     * Removes an infraction type this will cause every infraction going by that
     * type to UNDEFINED
     *
     * @param name
     */
    void removeInfractionType(String name);

    /* INFRACTIONS */
    /**
     * Adds an infraction
     *
     * @param infraction
     */
    void addInfraction(Infraction infraction);

    /**
     * Gets an infraction by its ID
     *
     * @param id
     * @return an infraction by the ID given
     */
    Infraction getInfraction(String id);

    /**
     * Gets the infraction(s) by the giver
     *
     * @param giver
     * @return all the infraction given by the user
     */
    List<Infraction> getInfractionByUser(User giver);

    /**
     * Gets the infraction(s) by the target
     *
     * @param giver
     * @return all the infraction given to the user
     */
    List<Infraction> getInfractionByGiver(User target);

    /**
     * Gets the infraction given by the giver to the target
     *
     * @param giver
     * @param target
     * @return every infraction given by giver to target
     */
    List<Infraction> getInfractionBy(User giver, User target);

    /**
     * Gets the infraction given between a specified time
     *
     * @param start
     * @param end
     * @return every infractions given in the given time
     */
    List<Infraction> getInfractionsBetween(Date start, Date end);

    /**
     * Gets all infractions
     *
     * @return every infractions
     */
    List<Infraction> getAllInfractions();

    /**
     * Updates an infraction given its ID by the infraction passed
     *
     * @param id
     * @param updatedInfraction
     */
    void updateInfraction(String id, Infraction updatedInfraction);

    /**
     * Removes an infraction by its ID This will remove every trace of that
     * infraction ever being given If you want to pardon a user but keep the
     * infraction record, see
     * {@link InfractionManager#updateInfractionEffective(java.lang.String, boolean)}
     *
     * @param id
     */
    void removeInfraction(String id);

    /**
     * Changes the effectivness of an infraction
     *
     * @param id
     * @param effective
     */
    void updateInfractionEffective(String id, boolean effective);
}
