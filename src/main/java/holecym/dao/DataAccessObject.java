package holecym.dao;

import holecym.dao.converters.DtoConverter;
import holecym.model.Appliance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Michal on 5. 1. 2017.
 */
public class DataAccessObject<T> extends SimpleDao<T> {

    public DataAccessObject() {
        super();
    }

    public Map<Appliance, Set<T>> getQuery
            (List<String> sqlQueries, Appliance[] appliances, DtoConverter<T> dtoConverter) {
        final Map<Appliance, Set<T>> result = new HashMap<>();

        try (Statement statement = connection.createStatement()) {
            for (int i = 0, sqlQueriesSize = sqlQueries.size(); i < sqlQueriesSize; i++) {
                final Set<T> dtoModelSet = new HashSet<>();
                String sqlQuery = sqlQueries.get(i);
                try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                    while (resultSet.next()) {
                        dtoConverter.convert(dtoModelSet, resultSet);
                    }
                    result.put(appliances[i], dtoModelSet);
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
