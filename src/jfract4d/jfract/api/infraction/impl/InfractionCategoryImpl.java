package jfract4d.jfract.api.infraction.impl;

import jfract4d.jfract.api.infraction.InfractionCategory;

/**
 * Basic Implementation of an infraction category
 * @author Antoine Gagnon
 */
public class InfractionCategoryImpl implements InfractionCategory {

    /**
     * Name of the infraction
     */
    private String name;

    /**
     * Points given by this infraction
     */
    private int points;

    /**
     * Internal ID of this infraction type If id is -1, it hasn't been set by
     * the datamanager
     */
    private int id = -1;

    public InfractionCategoryImpl(String name, int points) {
        this.name = name.toLowerCase();
        this.points = points;
    }

    public InfractionCategoryImpl(int id, String name, int points) {
        this.name = name.toLowerCase();
        this.points = points;
        setID(id);
    }

    /**
     * Sets the id of this category Internal, never manually use this
     *
     * @param id
     */
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return name + " | " + points + " pts";
    }
    
    
}
