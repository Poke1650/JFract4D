package jfract4d.discord.util;

import java.util.Random;

/**
 *
 * @author Antoine Gagnon
 */
public class FormatHelper {

    public static final int DISCORD_ID_LENGTH = 18;
    public static final int INFRACTION_ID_LENGTH = 8;
    
    public static boolean isValidID(String id) {
        return FormatHelper.isNumeric(id) && FormatHelper.isIDLengthValid(id);
    }
    
    public static boolean isIDLengthValid(String id) {
        return id.length() == DISCORD_ID_LENGTH;
    }
    
    public static boolean isNumeric(String id) {
        return id.matches("[0-9]+");
    }
    
    public static String generateInfractionID() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < INFRACTION_ID_LENGTH){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, INFRACTION_ID_LENGTH);
    }
}
