package jfract4d.discord.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.jfract.api.infraction.impl.InfractionImpl;
import jfract4d.jfract.api.infraction.impl.InfractionCategoryImpl;
import jfract4d.jfract.api.infraction.impl.InfractionTypeImpl;
import jfract4d.discord.user.DiscordRole;
import jfract4d.discord.user.DiscordUser;
import jfract4d.jfract.JFract;

import jfract4d.jfract.api.data.InfractionManager;
import jfract4d.jfract.api.exception.MalformedIDException;
import jfract4d.jfract.api.infraction.Infraction;
import jfract4d.jfract.api.infraction.InfractionCategory;
import jfract4d.jfract.api.infraction.InfractionType;
import jfract4d.jfract.api.user.User;
import jfract4d.jfract.helper.DateHelper;

/**
 *  Infraction Manager implementation for
 * @author Antoine Gagnon
 */
public class DiscordInfractionManager implements InfractionManager {

    DiscordInfractionManager() {

    }

    @Override
    public void addInfractionCategory(InfractionCategory icategory) throws SQLException {

        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("INSERT INTO infraction_category (name, points) VALUES (?,?)");

            stat.setString(1, icategory.getName());
            stat.setInt(2, icategory.getPoints());

            stat.executeUpdate();

        }
    }

    @Override
    public InfractionCategory getInfractionCategory(String name) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("SELECT * FROM infraction_category WHERE name = ?");

            stat.setString(1, name);

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                return new InfractionCategoryImpl(rs.getInt("id"), rs.getString("name"), rs.getInt("points"));
            }

        }
        return null;
    }

    public InfractionCategory getInfractionCategory(int id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("SELECT * FROM infraction_category WHERE id = ?");

            stat.setInt(1, id);

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                return new InfractionCategoryImpl(rs.getInt("id"), rs.getString("name"), rs.getInt("points"));
            }

        }
        return null;
    }

    @Override
    public List<InfractionCategory> getInfractionCategories() throws SQLException {

        List<InfractionCategory> list = new ArrayList<>();
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("SELECT * FROM infraction_category");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                list.add(new InfractionCategoryImpl(rs.getInt("id"), rs.getString("name"), rs.getInt("points")));
            }

        }
        return list;
    }

    @Override
    public void updateInfractionCategory(String name, InfractionCategory updatedICategory) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "UPDATE infraction_category SET points = ? WHERE name = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setInt(1, updatedICategory.getPoints());
            stat.setString(2, name);

            stat.executeUpdate();

        }
    }

    @Override
    public void removeInfractionCategory(String name) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "DELETE FROM infraction_category WHERE name = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, name);
            stat.executeUpdate();

        }
    }

    @Override
    public void addInfractionType(InfractionType itype) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "INSERT INTO infraction_type (name, description, category) VALUES (?,?,?)";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, itype.getName());
            stat.setString(2, itype.getDescription());
            stat.setInt(3, itype.getCategory().getID());

            stat.executeUpdate();

        }
    }

    @Override
    public InfractionType getInfractionType(String name) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("SELECT infraction_type.*, infraction_category.name AS 'c_name', infraction_category.points FROM `infraction_type` LEFT JOIN infraction_category on infraction_type.category = infraction_category.id WHERE infraction_type.name = ?");

            stat.setString(1, name);

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                return new InfractionTypeImpl(
                        rs.getString("name"),
                        rs.getString("description"),
                        new InfractionCategoryImpl(
                                rs.getInt("category"),
                                rs.getString("c_name"),
                                rs.getInt("points")
                        )
                ).withID(rs.getInt("id"));
            }

        }
        return null;
    }

    @Override
    public InfractionType getInfractionType(int id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("SELECT infraction_type.*, infraction_category.name AS 'c_name', infraction_category.points FROM `infraction_type` LEFT JOIN infraction_category on infraction_type.category = infraction_category.id WHERE infraction_type.id = ?");

            stat.setInt(1, id);

            ResultSet rs = stat.executeQuery();

            //TODO: make a DiscordInfractionTypeBuilder
            if (rs.next()) {
                return new InfractionTypeImpl(
                        rs.getString("name"),
                        rs.getString("description"),
                        new InfractionCategoryImpl(
                                rs.getInt("category"),
                                rs.getString("c_name"),
                                rs.getInt("points")
                        )
                ).withID(rs.getInt("id"));
            }

        }
        return null;
    }

    @Override
    public List<InfractionType> getInfractionTypes() throws SQLException {
        List<InfractionType> list = new ArrayList<>();
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement("SELECT infraction_type.*, infraction_category.name AS 'c_name', infraction_category.points FROM `infraction_type` LEFT JOIN infraction_category on infraction_type.category = infraction_category.id");
            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                list.add(new InfractionTypeImpl(
                        rs.getString("name"),
                        rs.getString("description"),
                        new InfractionCategoryImpl(
                                rs.getInt("category"),
                                rs.getString("c_name"),
                                rs.getInt("points")
                        )
                ).withID(rs.getInt("id"))
                );
            }

        }
        return list;
    }

    @Override
    public void updateInfractionType(String name, InfractionType updatedIType) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "UPDATE infraction_type SET name = ?, description = ?, category = ? WHERE infraction_type.name = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, updatedIType.getName());
            stat.setString(2, updatedIType.getDescription());
            stat.setInt(3, updatedIType.getCategory().getID());
            stat.setString(4, name);

            stat.executeUpdate();

        }
    }

    @Override
    public void updateInfractionType(int id, InfractionType updatedIType) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "UPDATE infraction_type SET name = ?, description = ?, category = ? WHERE infraction_type.id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, updatedIType.getName());
            stat.setString(2, updatedIType.getDescription());
            stat.setInt(3, updatedIType.getCategory().getID());
            stat.setInt(4, id);

            stat.executeUpdate();

        }
    }

    @Override
    public void removeInfractionType(String name) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "DELETE FROM infraction_type WHERE infraction_type.name = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, name);
            stat.executeUpdate();

        }
    }

    @Override
    public void removeInfractionType(int id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "DELETE FROM infraction_type WHERE infraction_type.id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setInt(1, id);
            stat.executeUpdate();

        }
    }

    @Override
    public void addInfraction(Infraction infraction) throws SQLException {

        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement
                    = "INSERT INTO `infraction` (`id`, `user`, `giver`, `infraction_type`, `time`, `effective`)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, infraction.getID());
            stat.setString(2, infraction.getTarget().getID());
            stat.setString(3, infraction.getGiver().getID());
            stat.setInt(4, infraction.getType().getID());
            stat.setString(5, DateHelper.toSQLDateTime(infraction.getTime()));
            stat.setBoolean(6, infraction.isEffective());

            stat.executeUpdate();

        }
    }

    private static final String INFRACTION_QUERY = "SELECT infraction.id,"
            + " infraction.giver, ugiver.role AS 'grid', rgiver.name AS 'grn', rgiver.level AS 'grl',"
            + " infraction.user, utarget.role AS 'trid', rtarget.name AS 'trn', rtarget.level AS 'trl',"
            + " infraction.time,"
            + " itype.id AS 'itid', itype.name AS 'itypename', itype.description AS 'itypedesc',"
            + " icat.id AS 'icatid', icat.name AS 'icatname', icat.points AS 'icatpoint',"
            + " infraction.effective"
            + " FROM infraction"
            + " JOIN user ugiver ON infraction.giver = ugiver.discord_id"
            + " JOIN user utarget ON infraction.user = utarget.discord_id"
            + " JOIN role rgiver ON ugiver.role = rgiver.id"
            + " JOIN role rtarget ON utarget.role = rtarget.id"
            + " JOIN infraction_type itype ON infraction.infraction_type = itype.id"
            + " JOIN infraction_category icat ON itype.category = icat.id";

    @Override
    public Infraction getInfraction(String id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement(INFRACTION_QUERY + " WHERE infraction.id = ?");

            stat.setString(1, id);

            ResultSet rs = stat.executeQuery();

            if (rs.next()) {
                return new InfractionImpl(
                        id,
                        new DiscordUser(rs.getString("giver"), new DiscordRole(rs.getString("grid"), rs.getString("grn"), rs.getInt("grl"))),
                        new DiscordUser(rs.getString("user"), new DiscordRole(rs.getString("trid"), rs.getString("trn"), rs.getInt("trl"))),
                        rs.getDate("time"),
                        new InfractionTypeImpl(rs.getString("itypename"), rs.getString("itypedesc"),
                                new InfractionCategoryImpl(rs.getInt("icatid"), rs.getString("icatname"), rs.getInt("icatpoint"))).withID(rs.getInt("itid")),
                        rs.getBoolean("effective")
                );
            }

        } catch (MalformedIDException ex) {
            JFract.LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Infraction> getInfractionByUser(User giver) throws SQLException {

        List<Infraction> list = new ArrayList();
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement(INFRACTION_QUERY + " WHERE infraction.giver = ?");

            stat.setString(1, giver.getID());

            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                list.add(new InfractionImpl(
                        rs.getString("id"),
                        new DiscordUser(rs.getString("giver"), new DiscordRole(rs.getString("grid"), rs.getString("grn"), rs.getInt("grl"))),
                        new DiscordUser(rs.getString("user"), new DiscordRole(rs.getString("trid"), rs.getString("trn"), rs.getInt("trl"))),
                        rs.getDate("time"),
                        new InfractionTypeImpl(rs.getString("itypename"), rs.getString("itypedesc"),
                                new InfractionCategoryImpl(rs.getInt("icatid"), rs.getString("icatname"), rs.getInt("icatpoint"))).withID(rs.getInt("itid")),
                        rs.getBoolean("effective")
                ));
            }

        } catch (MalformedIDException ex) {
            JFract.LOGGER.log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Infraction> getInfractionForTarget(User target) throws SQLException {
        List<Infraction> list = new ArrayList();
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement(INFRACTION_QUERY + " WHERE infraction.user = ?");

            stat.setString(1, target.getID());

            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                list.add(new InfractionImpl(
                        rs.getString("id"),
                        new DiscordUser(rs.getString("giver"), new DiscordRole(rs.getString("grid"), rs.getString("grn"), rs.getInt("grl"))),
                        new DiscordUser(rs.getString("user"), new DiscordRole(rs.getString("trid"), rs.getString("trn"), rs.getInt("trl"))),
                        rs.getDate("time"),
                        new InfractionTypeImpl(rs.getString("itypename"), rs.getString("itypedesc"),
                                new InfractionCategoryImpl(rs.getInt("icatid"), rs.getString("icatname"), rs.getInt("icatpoint"))).withID(rs.getInt("itid")),
                        rs.getBoolean("effective")
                ));
            }

        } catch (MalformedIDException ex) {
            JFract.LOGGER.log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Infraction> getInfractionBy(User giver, User target) throws SQLException {
        List<Infraction> list = new ArrayList();
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement(INFRACTION_QUERY + " WHERE infraction.user = ? AND infraction.giver = ?");

            stat.setString(1, target.getID());
            stat.setString(2, giver.getID());

            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                list.add(new InfractionImpl(
                        rs.getString("id"),
                        new DiscordUser(rs.getString("giver"), new DiscordRole(rs.getString("grid"), rs.getString("grn"), rs.getInt("grl"))),
                        new DiscordUser(rs.getString("user"), new DiscordRole(rs.getString("trid"), rs.getString("trn"), rs.getInt("trl"))),
                        rs.getDate("time"),
                        new InfractionTypeImpl(rs.getString("itypename"), rs.getString("itypedesc"),
                                new InfractionCategoryImpl(rs.getInt("icatid"), rs.getString("icatname"), rs.getInt("icatpoint"))).withID(rs.getInt("itid")),
                        rs.getBoolean("effective")
                ));
            }

        } catch (MalformedIDException ex) {
            JFract.LOGGER.log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Infraction> getInfractionsBetween(Date start, Date end) throws SQLException {
        List<Infraction> list = new ArrayList();
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement(INFRACTION_QUERY + " WHERE infraction.time >= ? AND infraction.time <= ?");

            stat.setString(1, DateHelper.toSQLDateTime(start));
            stat.setString(2, DateHelper.toSQLDateTime(end));

            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                list.add(new InfractionImpl(
                        rs.getString("id"),
                        new DiscordUser(rs.getString("giver"), new DiscordRole(rs.getString("grid"), rs.getString("grn"), rs.getInt("grl"))),
                        new DiscordUser(rs.getString("user"), new DiscordRole(rs.getString("trid"), rs.getString("trn"), rs.getInt("trl"))),
                        rs.getDate("time"),
                        new InfractionTypeImpl(rs.getString("itypename"), rs.getString("itypedesc"),
                                new InfractionCategoryImpl(rs.getInt("icatid"), rs.getString("icatname"), rs.getInt("icatpoint"))).withID(rs.getInt("itid")),
                        rs.getBoolean("effective")
                ));
            }

        } catch (MalformedIDException ex) {
            JFract.LOGGER.log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Infraction> getAllInfractions() throws SQLException {
        List<Infraction> list = new ArrayList();
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            PreparedStatement stat = conn.prepareStatement(INFRACTION_QUERY);

            ResultSet rs = stat.executeQuery();

            while (rs.next()) {
                list.add(new InfractionImpl(
                        rs.getString("id"),
                        new DiscordUser(rs.getString("giver"), new DiscordRole(rs.getString("grid"), rs.getString("grn"), rs.getInt("grl"))),
                        new DiscordUser(rs.getString("user"), new DiscordRole(rs.getString("trid"), rs.getString("trn"), rs.getInt("trl"))),
                        rs.getDate("time"),
                        new InfractionTypeImpl(rs.getString("itypename"), rs.getString("itypedesc"),
                                new InfractionCategoryImpl(rs.getInt("icatid"), rs.getString("icatname"), rs.getInt("icatpoint"))).withID(rs.getInt("itid")),
                        rs.getBoolean("effective")
                ));
            }

        } catch (MalformedIDException ex) {
            JFract.LOGGER.log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void updateInfraction(String id, Infraction updatedInfraction) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "UPDATE infraction SET user = ?, giver = ?, infraction_type = ?, time = ?, effective = ? WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, updatedInfraction.getTarget().getID());
            stat.setString(2, updatedInfraction.getGiver().getID());
            stat.setInt(3, updatedInfraction.getType().getID());
            stat.setString(4, DateHelper.toSQLDateTime(updatedInfraction.getTime()));
            stat.setBoolean(5, updatedInfraction.isEffective());
            stat.setString(6, id);

            stat.executeUpdate();
        }
    }

    @Override
    public void removeInfraction(String id) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "DELETE FROM infraction WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);

            stat.setString(1, id);
            stat.executeUpdate();

        }
    }

    @Override
    public void updateInfractionEffective(String id, boolean effective) throws SQLException {
        try (Connection conn = JFract.getDatabaseManager().getConnection()) {

            String statement = "UPDATE infraction SET effective = ? WHERE id = ?";
            PreparedStatement stat = conn.prepareStatement(statement);
            stat.setBoolean(1, effective);
            stat.setString(2, id);
            stat.executeUpdate();
        }
    }

}
