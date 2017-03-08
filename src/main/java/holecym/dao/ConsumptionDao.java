package holecym.dao;

import holecym.model.ConsumptionModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static holecym.utils.DateUtils.getDateFromTimestamp;


/**
 * Created by Michal on 5. 1. 2017.
 */
public class ConsumptionDao extends JdbcConnection {

    private static final String COLUMN_NAME = "Meridlo";
    private static final String COLUMN_UNIT = "Units";
    private static final String COLUMN_DATETIME = "Datetimestamp";
    private static final String COLUMN_FLOAT_VALUE = "odometervalue";
    private static final String COLUMN_ID = "parentid";

    public ConsumptionDao() {
        super();
    }

    public Map<String, Set<ConsumptionModel>> getMeasureValues(List<String> sqlQueries, String[] appliances) {
        final Map<String, Set<ConsumptionModel>> result = new HashMap<>();


        try (Statement statement = connection.createStatement()) {
            for (int i = 0, sqlQueriesSize = sqlQueries.size(); i < sqlQueriesSize; i++) {
                final Set<ConsumptionModel> consumptionModelSet = new HashSet<>();
                String sqlQuery = sqlQueries.get(i);
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    while (resultSet.next()) {
                        ConsumptionModel consumptionModel = new ConsumptionModel();
                        consumptionModel.setId(resultSet.getInt(COLUMN_ID));
                        consumptionModel.setName(resultSet.getString(COLUMN_NAME));
                        consumptionModel.setUnit(resultSet.getString(COLUMN_UNIT));
                        consumptionModel.setDatetime(getDateFromTimestamp(resultSet.getTimestamp(COLUMN_DATETIME)));
                        consumptionModel.setUsage(resultSet.getDouble(COLUMN_FLOAT_VALUE));

                        if (consumptionModel.getUsage() > 0) {
                            consumptionModelSet.add(consumptionModel);
                        }
                    }
                    result.put(appliances[i], consumptionModelSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return result;
    }

}
