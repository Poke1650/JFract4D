package jfract4d.discord.exception;

import jfract4d.discord.util.FormatHelper;

/**
 *
 * @author Antoine Gagnon
 */
public class MalformedDiscordIDException extends Exception {

    private String id;

    public MalformedDiscordIDException(String id) {
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
    public String getMessage() {
        return "Input length = " + getInputLength() + ", length valid = " + FormatHelper.isIDLengthValid(id) + ", Has Non mumeric characters = " + !FormatHelper.isNumeric(id);
    }
}
