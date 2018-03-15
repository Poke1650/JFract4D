/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.discord.user;

import java.util.logging.Level;
import java.util.logging.Logger;
import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.jfract.api.user.Role;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Antoine Gagnon
 */
public class DiscordRoleTest {

    private Role role;

    public DiscordRoleTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        try {
            DiscordRole goodRole = new DiscordRole("375764853895462912", "Admin", 0);
        } catch (MalformedDiscordIDException e) {
            fail(e.getMessage());
        }

        boolean trown = false;
        try {
            new DiscordRole("375asda5462912", "Admin", 0);
        } catch (Exception e) {
            trown = true;
        }
        
        assertTrue(trown);
        trown = false;

        try {
            new DiscordRole("", "Admin", 0);
        } catch (Exception e) {
            trown = true;
        }

        assertTrue(trown);
        trown = false;
        
        try {
            new DiscordRole("2912", "Admin", 0);
        } catch (Exception e) {
            trown = true;
        }
        
        assertTrue(trown);
        trown = false;

    }

    /**
     * Test of getName method, of class DiscordRole.
     */
    @Test
    public void testGetName() {

        DiscordRole instance = null;
        try {
            instance = new DiscordRole("375764853895462912", "Admin", 0);
        } catch (MalformedDiscordIDException ex) {
            Logger.getLogger(DiscordRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(instance.getName(), "Admin");
    }

    /**
     * Test of getLevel method, of class DiscordRole.
     */
    @Test
    public void testGetLevel() {
        DiscordRole instance = null;
        try {
            instance = new DiscordRole("375764853895462912", "Admin", 0);
        } catch (MalformedDiscordIDException ex) {
            Logger.getLogger(DiscordRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(instance.getLevel(), 0);
    }

    /**
     * Test of compareTo method, of class DiscordRole.
     */
    @Test
    public void testCompareTo() {
        DiscordRole instance = null;
        DiscordRole higher = null;
        DiscordRole lower = null;
        DiscordRole equal = null;

        try {
            instance = new DiscordRole("375764853895462912", "Admin", 1);
            higher = new DiscordRole("375764853895462912", "Admin", 2);
            equal = new DiscordRole("375764853895462912", "Admin", 1);
            lower = new DiscordRole("375764853895462912", "Admin", 0);
        } catch (MalformedDiscordIDException ex) {
            Logger.getLogger(DiscordRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(instance.compareTo(higher), -1);
        assertEquals(instance.compareTo(lower), 1);
        assertEquals(instance.compareTo(equal), 0);
    }

    /**
     * Test of getID method, of class DiscordRole.
     */
    @Test
    public void testGetID() {
        DiscordRole instance = null;
        try {
            instance = new DiscordRole("375764853895462912", "Admin", 0);
        } catch (MalformedDiscordIDException ex) {
            Logger.getLogger(DiscordRoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(instance.getID(), "375764853895462912");
    }

}
