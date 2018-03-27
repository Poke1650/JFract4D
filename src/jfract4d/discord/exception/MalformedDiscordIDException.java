package jfract4d.discord.exception;

import jfract4d.discord.util.FormatHelper;
import jfract4d.jfract.api.exception.MalformedIDException;

/**
 * Exception thrown when a Discord ID is malformed.
 * A Discord ID should follow the following format:
 *  -18 characters
 *  -Only numeric characters
 * @author Antoine Gagnon
 */
public class MalformedDiscordIDException extends MalformedIDException {

    public MalformedDiscordIDException(String id) {
        super(id);
    }

}
