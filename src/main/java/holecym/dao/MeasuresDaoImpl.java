package holecym.dao;

import holecym.api.MeasuresDao;
import holecym.model.Measures;
import holecym.model.Monitor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Michal on 5. 1. 2017.
 */
public class MeasuresDaoImpl extends JdbcConnection implements MeasuresDao {

    private static final String COLUMN_ID = "Parent_ID";
    private static final String COLUMN_NAME = "Meridlo";
    private static final String COLUMN_UNIT = "Units";

    public MeasuresDaoImpl() {
        super();
    }

    public List<Measures> getMeasureValues() {
        final List<Measures> measuresList = new ArrayList<>();
        getMeasuresDataResultSet();
        try {
            while (resultSet.next()) {
                Measures measures = new Measures();
                measures.setId(resultSet.getInt(COLUMN_ID));
                measures.setName(resultSet.getString(COLUMN_NAME));
                measures.setUnit(resultSet.getString(COLUMN_UNIT));

                measuresList.add(measures);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return measuresList;
    }

    private void getMeasuresDataResultSet() {
        try {
            final String sql = "SELECT  [Parent_ID]\n" +
                    ",[Meridlo]\n" +
                    ",[Units]\n" +
                    "  FROM [TEST].[dbo].[tbl_merice]";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
