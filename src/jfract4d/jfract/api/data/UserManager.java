/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.jfract.api.data;

import java.util.List;
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.User;

/**
 *
 * @author Antoine Gagnon
 */
public interface UserManager {
    
    /**
     * Add a user to the database
     * @param user 
     */
    void addUser(User user);
    
    /**
     * Remove a user from the database
     * @param user 
     */
    void removeUser(String id);
    
    /**
     *
     * @return every users in the database
     */
    List<User> getUsers();
    
    /**
     * Return the role of a user
     * @param user
     * @return the role of the given user
     */
    Role getRoleForUser(User user);
    
    /**
     * Return the role of a user
     * @param user
     * @return the role of the user for the given id
     */
    Role getRoleForUser(String id);
    
    /**
     * 
     * @param id
     * @return 
     */
    Role getRole(String id);
    
    /**
     * Adds a role to the database
     * @param role
     */
    void addRole(Role role);
    
    
    /**
     * Update a given role by the updated role
     * @param role
     */
    void updateRole(Role role);
    
    /**
     * 
     * @return every roles 
     */
    List<Role> getRoles();
}
