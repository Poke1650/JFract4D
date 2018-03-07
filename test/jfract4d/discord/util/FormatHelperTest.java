/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.discord.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Antoine Gagnon
 */
public class FormatHelperTest {
    
    public FormatHelperTest() {
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

    /**
     * Test of isValidID method, of class FormatHelper.
     */
    @Test
    public void testIsValidID() {
        System.out.println("isValidID");
        String id = "";
        boolean expResult = false;
        boolean result = FormatHelper.isValidID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isIDLengthValid method, of class FormatHelper.
     */
    @Test
    public void testIsIDLengthValid() {
        System.out.println("isIDLengthValid");
        String id = "";
        boolean expResult = false;
        boolean result = FormatHelper.isIDLengthValid(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNumeric method, of class FormatHelper.
     */
    @Test
    public void testIsNumeric() {
       
        assertTrue(FormatHelper.isNumeric("146515"));
        assertTrue(FormatHelper.isNumeric("13423423523523523546515"));
        assertFalse(FormatHelper.isNumeric("a23523523546515"));
        assertFalse(FormatHelper.isNumeric("aaarwrwe"));
    }

    /**
     * Test of isLetters method, of class FormatHelper.
     */
    @Test
    public void testIsLetters() {
        System.out.println("isLetters");
        String string = "";
        boolean expResult = false;
        boolean result = FormatHelper.isLetters(string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateInfractionID method, of class FormatHelper.
     */
    @Test
    public void testGenerateInfractionID() {
        System.out.println("generateInfractionID");
        String expResult = "";
        String result = FormatHelper.generateInfractionID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
