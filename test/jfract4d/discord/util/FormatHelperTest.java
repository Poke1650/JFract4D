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
        assertTrue(FormatHelper.isValidID("157563215994290176"));
        assertFalse(FormatHelper.isValidID(""));
        assertFalse(FormatHelper.isValidID("15755555555555555555555563215994290176"));
        assertFalse(FormatHelper.isValidID("157563215994290176AAAAAABDFSDF"));
        assertFalse(FormatHelper.isValidID("AAAAABDFSDF"));
        assertFalse(FormatHelper.isValidID("61485ABD651635FSDF15615"));
    }

    /**
     * Test of isIDLengthValid method, of class FormatHelper.
     */
    @Test
    public void testIsIDLengthValid() {
        assertTrue(FormatHelper.isIDLengthValid("111111111111111111")); //18 of lenght
        assertFalse(FormatHelper.isIDLengthValid(""));
        assertFalse(FormatHelper.isIDLengthValid("1111"));
        assertFalse(FormatHelper.isIDLengthValid("111111111111111111111111111111111111111111111111111111"));
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
        assertTrue(FormatHelper.isLetters("ABCabc"));
        assertFalse(FormatHelper.isLetters("ABCabc!@#"));
        assertFalse(FormatHelper.isLetters(""));
        assertFalse(FormatHelper.isLetters("123"));
    }

    /**
     * Test of generateInfractionID method, of class FormatHelper.
     */
    @Test
    public void testGenerateInfractionID() {
        assertEquals(FormatHelper.generateInfractionID().length(), 8);
        assertTrue(FormatHelper.generateInfractionID().matches("^[a-zA-Z0-9]*$"));
    }    
}
