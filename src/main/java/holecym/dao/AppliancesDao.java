package holecym.dao;

import holecym.model.Appliance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Michal on 13. 3. 2017.
 */
public class AppliancesDao extends JdbcConnection {

    private static final String COLUMN_NAME = "Meridlo";
    private static final String COLUMN_UNIT = "Units";
    private static final String COLUMN_ID = "Parent_ID";
    private static final String COLUMN_PATH = "Path";
    private static final String COLUMN_CC = "cc";


    public AppliancesDao() {
        super();
    }

    public Set<Appliance> getAppliances(String sqlQuery) {
        final Set<Appliance> appliances = new LinkedHashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                while (resultSet.next()) {
                    Appliance appliance = new Appliance();
                    appliance.setName(resultSet.getString(COLUMN_NAME));
                    appliance.setUnit(resultSet.getString(COLUMN_UNIT));
                    appliance.setId(resultSet.getInt(COLUMN_ID));
                    appliance.setCc(resultSet.getString(COLUMN_CC));
                    appliance.setPath(resultSet.getString(COLUMN_PATH));

                    appliances.add(appliance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return appliances;
    }
}
