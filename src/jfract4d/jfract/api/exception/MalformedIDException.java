package jfract4d.jfract.api.exception;

import jfract4d.discord.util.FormatHelper;

/**
 *
 * @author Antoine Gagnon
 */
public class MalformedIDException extends Exception{
    /**
     * The malformed ID
     */
    private String id;

    public MalformedIDException(String id) {
        this.id = id;
    }

    /**
     * Returns the length of the input.
     *
     * @return the length of the input
     */
    public int getInputLength() {
        return id.length();
    }

    /**
     * Returns the message.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return "Input length = " + getInputLength() + ", length valid = " + FormatHelper.isIDLengthValid(id) + ", Has Non mumeric characters = " + !FormatHelper.isNumeric(id);
    }
}
