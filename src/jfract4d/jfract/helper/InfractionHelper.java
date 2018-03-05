package jfract4d.jfract.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jfract4d.discord.util.FormatHelper;
import jfract4d.jfract.JFract;

/**
 *
 * @author Antoine Gagnon
 */
public class InfractionHelper {

    /**
     * Gets the next unique infraction id
     * @return
     * @throws SQLException 
     */
    public static String getNextInfractionID() throws SQLException {

        String id;
        do {
            id = FormatHelper.generateInfractionID();
        } while (InfractionHelper.infractionIDTaken(id));

        return id;
    }

    /**
     * Looks in the database if there's any infraction with the given ID
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public static boolean infractionIDTaken(String id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {
            PreparedStatement stat = conn.prepareStatement("SELECT 1 FROM infraction WHERE id = ?");
            stat.setString(1, id);

            ResultSet result = stat.executeQuery();

            return result.next();
        }
    }
}
