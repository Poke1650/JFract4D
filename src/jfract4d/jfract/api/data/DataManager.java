/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.jfract.api.data;

/**
 *
 * @author Antoine Gagnon
 */
public interface DataManager {
    
    /**
     * 
     * @return the infraction manager instance 
     */
    InfractionManager getInfractionManager();

    /**
     * 
     * @return the user manager instance 
     */
    UserManager getUserManager();
}
