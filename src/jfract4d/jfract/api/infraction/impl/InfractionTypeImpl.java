package jfract4d.jfract.api.infraction.impl;

import jfract4d.jfract.api.infraction.InfractionCategory;
import jfract4d.jfract.api.infraction.InfractionType;

/**
 * Basic Implementation of an infraction type
 * @author Antoine Gagnon
 */
public class InfractionTypeImpl implements InfractionType {

    /**
     * Name of the infraction type
     */
    String name;

    /**
     * Description of the type
     */
    String description;

    /**
     * Category in which this infraction fall into
     */
    InfractionCategory icategory;

    /**
     * Internal ID of this infraction type If id is -1, it hasn't been set by
     * the datamanager
     */
    private int id = -1;

    public InfractionTypeImpl(String name, String description, InfractionCategory category) {
        this.name = name.toLowerCase();
        setCategory(category);
        setDescription(description);
    }

    /**
     * Sets the id of this infraction Internal, never manually use this
     *
     * @param id
     * @return the instance
     */
    public InfractionTypeImpl withID(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setCategory(InfractionCategory iCategory) {
        this.icategory = iCategory;
    }

    @Override
    public InfractionCategory getCategory() {
        return icategory;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return name + " | " + getCategory().getPoints() + " pts | " + getCategory().getName();
    }
    
    

}
