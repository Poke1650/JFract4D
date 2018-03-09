/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.jfract.api.data;

import java.sql.SQLException;
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
     *
     * @param user
     * @throws java.sql.SQLException
     */
    void addUser(User user) throws SQLException;

    /**
     * Add all users on the list
     *
     * @param user
     * @throws java.sql.SQLException
     */
    void addUsers(List<User> user) throws SQLException;

    /**
     * Remove a user from the database
     *
     * @param id
     * @throws java.sql.SQLException
     */
    void removeUser(String id) throws SQLException;

    /**
     * Change the current role of the use by newRole
     *
     * @param user_id
     * @param newRole
     * @throws java.sql.SQLException
     */
    void updateUserRole(String user_id, Role newRole) throws SQLException;

    /**
     *
     * @return every users in the database
     * @throws java.sql.SQLException
     */
    List<User> getUsers() throws SQLException;

    /**
     * Return the role of a user
     *
     * @param user
     * @return the role of the given user
     * @throws java.sql.SQLException
     */
    Role getRoleForUser(User user) throws SQLException;

    /**
     * Return the role of a user
     *
     * @param id
     * @return the role of the user for the given id
     * @throws java.sql.SQLException
     */
    Role getRoleForUser(String id) throws SQLException;

    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    Role getRole(String id) throws SQLException;

    /**
     * Adds a role to the database
     *
     * @param role
     * @throws java.sql.SQLException
     */
    void addRole(Role role) throws SQLException;

    /**
     * Update a given role by the updated role
     *
     * @param id
     * @param role
     * @throws java.sql.SQLException
     */
    void updateRole(String id, Role role) throws SQLException;

    /**
     * Remove a role by its ID
     *
     * @param id
     * @throws java.sql.SQLException
     */
    void removeRole(String id) throws SQLException;

    /**
     *
     * @return every roles
     * @throws java.sql.SQLException
     */
    List<Role> getRoles() throws SQLException;
}
